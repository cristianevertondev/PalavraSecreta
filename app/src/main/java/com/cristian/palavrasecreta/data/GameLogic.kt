package com.cristian.palavrasecreta.data

import com.cristian.palavrasecreta.data.model.Chapter
import com.cristian.palavrasecreta.data.model.Level
import java.text.Normalizer

/**
 * Funções puras da lógica do jogo, isoladas do Android para serem testáveis.
 */
object GameLogic {

    /**
     * Normaliza uma resposta: remove acentos e converte para maiúsculas.
     * Permite que "maca" seja aceita para a palavra "MAÇÃ".
     */
    fun normalizeAnswer(text: String): String {
        val decomposed = Normalizer.normalize(text.trim(), Normalizer.Form.NFD)
        return decomposed.replace(Regex("\\p{M}"), "").uppercase()
    }

    /**
     * Um nível é desbloqueado quando é o primeiro da ordem global ou quando o
     * nível anterior foi concluído. Progressão linear entre e dentro dos capítulos.
     */
    fun isLevelUnlocked(allLevels: List<Level>, completed: Set<Int>, levelId: Int): Boolean {
        val index = allLevels.indexOfFirst { it.id == levelId }
        if (index < 0) return false
        if (index == 0) return true
        return allLevels[index - 1].id in completed
    }

    /**
     * Um capítulo é desbloqueado quando é o primeiro da coleção ou quando o
     * capítulo anterior foi totalmente concluído. A coleção é a fonte de verdade,
     * então o último capítulo é reconhecido por posição, sem número hardcoded.
     */
    fun isChapterUnlocked(chapters: List<Chapter>, completed: Set<Int>, chapterId: Int): Boolean {
        val index = chapters.indexOfFirst { it.id == chapterId }
        if (index < 0) return false
        if (index == 0) return true
        val previous = chapters[index - 1]
        return previous.levels.isNotEmpty() && previous.levels.all { it.id in completed }
    }
}