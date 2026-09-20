package com.cristian.palavrasecreta.data.content

import androidx.compose.ui.graphics.Color
import com.cristian.palavrasecreta.data.model.Chapter
import com.cristian.palavrasecreta.data.model.ChapterVisualTheme
import com.cristian.palavrasecreta.data.model.Difficulty
import com.cristian.palavrasecreta.data.model.Level

/**
 * Conteúdo estático do jogo — fonte única de verdade para capítulos e níveis.
 *
 * Arquitetura orientada por dados: a coleção [chapters] é tudo que a UI, o
 * ViewModel, a navegação, o DataStore e a lógica precisam. Para adicionar um
 * novo capítulo basta: criar o capítulo, definir o tema visual, adicionar os
 * níveis e incluir na lista [chapters]. Nenhuma alteração é necessária em
 * ViewModel/navegação/DataStore/GameLogic/telas.
 *
 * Os ids dos níveis são globais e sequenciais; a ordem global é derivada da
 * coleção (veja [allLevels]), nunca hardcoded.
 */
object GameContent {

    // ----------------------------------------------------------- helpers

    private fun theme(
        themeName: String,
        icon: String,
        primary: Long,
        secondary: Long,
        accent: Long,
        gradientTop: Long,
        gradientBottom: Long,
        decor: List<String>
    ) = ChapterVisualTheme(
        themeName = themeName,
        icon = icon,
        primary = Color(primary),
        secondary = Color(secondary),
        accent = Color(accent),
        gradientTop = Color(gradientTop),
        gradientBottom = Color(gradientBottom),
        decorEmojis = decor
    )

    private fun level(
        id: Int,
        chapterId: Int,
        word: String,
        hints: List<String>,
        difficulty: Difficulty,
        attempts: Int = 3
    ) = Level(id, chapterId, word, hints, difficulty, attempts)

    private fun chapter(
        id: Int,
        title: String,
        visualTheme: ChapterVisualTheme,
        levels: List<Level> = emptyList()
    ) = Chapter(id, title, visualTheme, levels)

    // ----------------------------------------------------------- capítulos

    val chapters: List<Chapter> = listOf(
        // Capítulo 1 — Frutas 🍎
        chapter(
            1, "Reino das Frutas",
            theme("Frutas", "🍎", 0xFF6DAA2C, 0xFFF5C942, 0xFFE85D3D, 0xFFFDF6D8, 0xFFCBE8A4,
                listOf("🍎", "🍌", "🍓", "🍊", "🍉")),
            listOf(
                level(1, 1, "BANANA", listOf(
                    "É uma fruta amarela e comprida.",
                    "Uma fruta que os macacos adoram comer."
                ), Difficulty.EASY),
                level(2, 1, "MAÇÃ", listOf(
                    "É uma fruta que pode ser vermelha ou verde.",
                    "Caiu na cabeça de um cientista famoso."
                ), Difficulty.EASY),
                level(3, 1, "LARANJA", listOf(
                    "É uma fruta cítrica e alaranjada.",
                    "Seu nome também é o nome de uma cor."
                ), Difficulty.EASY),
                level(4, 1, "MORANGO", listOf(
                    "É uma fruta vermelha com pequenas pintinhas.",
                    "Costuma aparecer em sorvetes e bolos."
                ), Difficulty.EASY),
                level(5, 1, "MELANCIA", listOf(
                    "É grande, verde por fora e vermelha por dentro.",
                    "Muito refrescante nos dias de calor."
                ), Difficulty.MEDIUM),
                level(6, 1, "ABACAXI", listOf(
                    "É uma fruta tropical com uma coroa de folhas.",
                    "Tem casca áspera e polpa bem suculenta."
                ), Difficulty.MEDIUM),
                level(7, 1, "MAMÃO", listOf(
                    "É uma fruta alaranjada comum no café da manhã.",
                    "Suas sementes são pretinhas e brilhantes."
                ), Difficulty.MEDIUM),
                level(8, 1, "MANGA", listOf(
                    "É uma fruta tropical, amarela e suculenta.",
                    "Tem um caroço grande no meio."
                ), Difficulty.MEDIUM),
                level(9, 1, "KIWI", listOf(
                    "É pequeno, marrom por fora e verde por dentro.",
                    "Tem o mesmo nome de um pássaro que não voa."
                ), Difficulty.HARD),
                level(10, 1, "COCO", listOf(
                    "É duro por fora e guarda água por dentro.",
                    "É usado em bolos, doces e água de coco."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 2 — Animais 🐾
        chapter(
            2, "Floresta dos Animais",
            theme("Animais", "🐾", 0xFF2E7D32, 0xFF4DB6AC, 0xFFE07A2B, 0xFFE4F2E3, 0xFFB2DFDB,
                listOf("🐾", "🐘", "🦒", "🐧", "🐬")),
            listOf(
                level(11, 2, "GATO", listOf(
                    "É um animal de estimação que mia.",
                    "Gosta de caçar ratos e dormir muito."
                ), Difficulty.EASY),
                level(12, 2, "CACHORRO", listOf(
                    "É conhecido como o melhor amigo do homem.",
                    "Ama roer ossos e brincar de buscar."
                ), Difficulty.EASY),
                level(13, 2, "TUBARÃO", listOf(
                    "É um peixe temido nos oceanos.",
                    "É o protagonista de filmes famosos de terror."
                ), Difficulty.MEDIUM),
                level(14, 2, "ELEFANTE", listOf(
                    "É o maior animal terrestre do mundo.",
                    "Tem uma tromba muito comprida."
                ), Difficulty.MEDIUM),
                level(15, 2, "GIRAFA", listOf(
                    "É o animal mais alto do planeta.",
                    "Tem o pescoço muito comprido."
                ), Difficulty.MEDIUM),
                level(16, 2, "LEÃO", listOf(
                    "É conhecido como o rei da selva.",
                    "O macho tem uma grande juba."
                ), Difficulty.MEDIUM),
                level(17, 2, "PINGUIM", listOf(
                    "É uma ave que não voa e adora o frio.",
                    "Anda bamboleando no gelo."
                ), Difficulty.HARD),
                level(18, 2, "GOLFINHO", listOf(
                    "É um mamífero marinho muito inteligente.",
                    "Adora pular sobre a água."
                ), Difficulty.HARD),
                level(19, 2, "VAGA-LUME", listOf(
                    "É um inseto que brilha no escuro.",
                    "Produz luz própria durante a noite."
                ), Difficulty.HARD),
                level(20, 2, "CAMALEÃO", listOf(
                    "É um lagarto que muda de cor.",
                    "Tem olhos que se movem de forma independente."
                ), Difficulty.HARD)
            )
        ),
        // Capítulos 3–30 — agora com níveis completos (10 por capítulo, ids globais 21–300).
        chapter(
            3, "Sabores do Mundo",
            theme("Comidas", "🍕", 0xFFE53935, 0xFFFB8C00, 0xFF6D4C41, 0xFFFFF3E0, 0xFFFFCCBC,
                listOf("🍕", "🌮", "🍝", "🍦", "🍩")),
            listOf(
                level(21, 3, "PIZZA", listOf(
                    "Prato italiano redondo e recheado.",
                    "É coberto com queijo derretido."
                ), Difficulty.EASY),
                level(22, 3, "PÃO", listOf(
                    "É feito de farinha e fermento.",
                    "Companheiro do café da manhã."
                ), Difficulty.EASY),
                level(23, 3, "BOLO", listOf(
                    "Doce assado para festas.",
                    "Pode ter cobertura de chocolate."
                ), Difficulty.EASY),
                level(24, 3, "ARROZ", listOf(
                    "Grão branquinho servido com feijão.",
                    "Base da refeição brasileira."
                ), Difficulty.EASY),
                level(25, 3, "FEIJÃO", listOf(
                    "Acompanha o arroz no prato.",
                    "É de cor escura e costuma ser cozido."
                ), Difficulty.MEDIUM),
                level(26, 3, "MACARRÃO", listOf(
                    "Massa em formato de tubo.",
                    "Leva molho de tomate por cima."
                ), Difficulty.MEDIUM),
                level(27, 3, "TACO", listOf(
                    "Comida mexicana em formato de concha.",
                    "É recheado com carne e queijo."
                ), Difficulty.MEDIUM),
                level(28, 3, "LASANHA", listOf(
                    "Prato de massa em camadas.",
                    "Tem molho e queijo entre as folhas."
                ), Difficulty.HARD),
                level(29, 3, "CHURRASCO", listOf(
                    "Carne assada na brasa.",
                    "Tradicional no sul do Brasil."
                ), Difficulty.HARD),
                level(30, 3, "EMPANADA", listOf(
                    "Salgado recheado e dobrado.",
                    "Tem massa crocante por fora."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 4 — Objetos do Cotidiano 🧸
        chapter(
            4, "Objetos do Cotidiano",
            theme("Objetos", "🧸", 0xFF5C6BC0, 0xFF8E24AA, 0xFF26A69A, 0xFFE8EAF6, 0xFFEDE7F6,
                listOf("🧸", "🛋️", "🔑", "📱", "📚")),
            listOf(
                level(31, 4, "CADEIRA", listOf(
                    "Serve para sentar.",
                    "Tem quatro pernas."
                ), Difficulty.EASY),
                level(32, 4, "MESA", listOf(
                    "Lugar onde se faz refeições.",
                    "Superfície apoiada em pernas."
                ), Difficulty.EASY),
                level(33, 4, "CANETA", listOf(
                    "Usada para escrever.",
                    "Pode ter tinta azul ou preta."
                ), Difficulty.EASY),
                level(34, 4, "LIVRO", listOf(
                    "Serve para ler.",
                    "Tem páginas e capa."
                ), Difficulty.EASY),
                level(35, 4, "TESOURA", listOf(
                    "Usada para cortar papel.",
                    "Tem duas lâminas cruzadas."
                ), Difficulty.MEDIUM),
                level(36, 4, "CHAVE", listOf(
                    "Abre portas e cadeados.",
                    "Fica presa em um chaveiro."
                ), Difficulty.MEDIUM),
                level(37, 4, "RELÓGIO", listOf(
                    "Mostra as horas.",
                    "Usado no pulso."
                ), Difficulty.MEDIUM),
                level(38, 4, "ESCADA", listOf(
                    "Serve para subir a lugares altos.",
                    "Tem degraus."
                ), Difficulty.HARD),
                level(39, 4, "ABRIDOR", listOf(
                    "Remove a tampa de garrafas.",
                    "Pequeno utensílio de cozinha."
                ), Difficulty.HARD),
                level(40, 4, "ALMOFADA", listOf(
                    "Usada para apoiar a cabeça.",
                    "Deixa o sofá macio."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 5 — Encantos da Natureza 🌿
        chapter(
            5, "Encantos da Natureza",
            theme("Natureza", "🌿", 0xFF43A047, 0xFF1E88E5, 0xFF795548, 0xFFE8F5E9, 0xFFB3E5FC,
                listOf("🌿", "🌳", "🌻", "🍄", "🌱")),
            listOf(
                level(41, 5, "ÁRVORE", listOf(
                    "Grande planta com tronco.",
                    "Dá sombra e frutos."
                ), Difficulty.EASY),
                level(42, 5, "FLOR", listOf(
                    "Parte colorida de uma planta.",
                    "Usada em buquês."
                ), Difficulty.EASY),
                level(43, 5, "RIO", listOf(
                    "Água que corre para o mar.",
                    "Pode ter cachoeiras."
                ), Difficulty.EASY),
                level(44, 5, "MAR", listOf(
                    "Grande extensão de água salgada.",
                    "Onde as ondas quebram."
                ), Difficulty.EASY),
                level(45, 5, "VULCÃO", listOf(
                    "Montanha que pode soltar lava.",
                    "Entra em erupção."
                ), Difficulty.MEDIUM),
                level(46, 5, "DESERTO", listOf(
                    "Lugar quente e cheio de areia.",
                    "Tem oásis e camelos."
                ), Difficulty.MEDIUM),
                level(47, 5, "CACHOEIRA", listOf(
                    "Queda de água.",
                    "Formada no curso de um rio."
                ), Difficulty.MEDIUM),
                level(48, 5, "FLORESTA", listOf(
                    "Grande área com muitas árvores.",
                    "Também chamada de mata."
                ), Difficulty.HARD),
                level(49, 5, "LAGOA", listOf(
                    "Água parada cercada por terra.",
                    "Menor que um mar."
                ), Difficulty.HARD),
                level(50, 5, "GALÁXIA", listOf(
                    "Conjunto de estrelas no espaço.",
                    "A Via Láctea é uma delas."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 6 — Rumo aos Lugares 🗺️
        chapter(
            6, "Rumo aos Lugares",
            theme("Lugares", "🗺️", 0xFF1E88E5, 0xFF00ACC1, 0xFF7CB342, 0xFFE3F2FD, 0xFFB2EBF2,
                listOf("🗺️", "🏖️", "🏔️", "🗼", "🌆")),
            listOf(
                level(51, 6, "CASA", listOf(
                    "Onde moramos.",
                    "Tem quartos e cozinha."
                ), Difficulty.EASY),
                level(52, 6, "ESCOLA", listOf(
                    "Lugar onde aprendemos.",
                    "Tem salas de aula."
                ), Difficulty.EASY),
                level(53, 6, "PARQUE", listOf(
                    "Lugar com árvores e brinquedos.",
                    "Bom para passear e brincar."
                ), Difficulty.EASY),
                level(54, 6, "IGREJA", listOf(
                    "Lugar de oração.",
                    "Tem torres ou sinos."
                ), Difficulty.EASY),
                level(55, 6, "FARMÁCIA", listOf(
                    "Lugar onde compramos remédios.",
                    "Tem balcão e corredores de medicamentos."
                ), Difficulty.MEDIUM),
                level(56, 6, "MUSEU", listOf(
                    "Guarda obras de arte e objetos antigos.",
                    "Lugar de exposições."
                ), Difficulty.MEDIUM),
                level(57, 6, "AEROPORTO", listOf(
                    "Onde os aviões pousam.",
                    "Local de embarque de viagens."
                ), Difficulty.MEDIUM),
                level(58, 6, "PRAIA", listOf(
                    "Areia na beira do mar.",
                    "Boa para tomar sol."
                ), Difficulty.HARD),
                level(59, 6, "BANCO", listOf(
                    "Instituição financeira.",
                    "Tem caixas eletrônicos."
                ), Difficulty.HARD),
                level(60, 6, "BIBLIOTECA", listOf(
                    "Lugar cheio de livros.",
                    "Serve para estudar e ler."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 7 — Mundo das Profissões 👨‍🚒
        chapter(
            7, "Mundo das Profissões",
            theme("Profissões", "👨‍🚒", 0xFF1A237E, 0xFFEF6C00, 0xFF00897B, 0xFFE8EAF6, 0xFFFFE0B2,
                listOf("👨‍🚒", "👩‍⚕️", "👨‍🍳", "👷", "🧑‍🔬")),
            listOf(
                level(61, 7, "MÉDICO", listOf(
                    "Cuida da nossa saúde.",
                    "Usa estetoscópio."
                ), Difficulty.EASY),
                level(62, 7, "BOMBEIRO", listOf(
                    "Apaga incêndios.",
                    "Usa caminhão vermelho."
                ), Difficulty.EASY),
                level(63, 7, "PROFESSOR", listOf(
                    "Ensina nas escolas.",
                    "Passa aulas para os alunos."
                ), Difficulty.EASY),
                level(64, 7, "POLICIAL", listOf(
                    "Mantém a ordem nas ruas.",
                    "Usa uma viatura."
                ), Difficulty.EASY),
                level(65, 7, "PADEIRO", listOf(
                    "Faz pães e bolos.",
                    "Trabalha com fornos."
                ), Difficulty.MEDIUM),
                level(66, 7, "ENCANADOR", listOf(
                    "Conserta canos e torneiras.",
                    "Ajuda a parar vazamentos."
                ), Difficulty.MEDIUM),
                level(67, 7, "CARPINTEIRO", listOf(
                    "Trabalha com madeira.",
                    "Faz móveis e mesas."
                ), Difficulty.MEDIUM),
                level(68, 7, "ASTRONAUTA", listOf(
                    "Viaja para o espaço.",
                    "Usa traje espacial."
                ), Difficulty.HARD),
                level(69, 7, "DENTISTA", listOf(
                    "Cuida dos dentes.",
                    "Usa broca e espelho."
                ), Difficulty.HARD),
                level(70, 7, "ELETRICISTA", listOf(
                    "Trabalha com fios e luz.",
                    "Instala lâmpadas e tomadas."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 8 — Universo Digital 💻
        chapter(
            8, "Universo Digital",
            theme("Tecnologia", "💻", 0xFF7E57C2, 0xFF42A5F5, 0xFF26C6DA, 0xFFF3E5F5, 0xFFE3F2FD,
                listOf("💻", "📱", "🖥️", "🕹️", "💾")),
            listOf(
                level(71, 8, "CELULAR", listOf(
                    "Aparelho que faz ligações.",
                    "Usado para usar aplicativos."
                ), Difficulty.EASY),
                level(72, 8, "COMPUTADOR", listOf(
                    "Máquina que processa dados.",
                    "Tem teclado e monitor."
                ), Difficulty.EASY),
                level(73, 8, "INTERNET", listOf(
                    "Rede mundial de computadores.",
                    "Usada para navegar e pesquisar."
                ), Difficulty.EASY),
                level(74, 8, "TELEVISÃO", listOf(
                    "Aparelho para assistir programas.",
                    "Mostra imagens em uma tela."
                ), Difficulty.EASY),
                level(75, 8, "TABLET", listOf(
                    "Aparelho parecido com um celular grande.",
                    "Tem tela de toque."
                ), Difficulty.MEDIUM),
                level(76, 8, "VIDEOGAME", listOf(
                    "Usado para jogar.",
                    "Tem controle e fases."
                ), Difficulty.MEDIUM),
                level(77, 8, "SENHA", listOf(
                    "Palavra secreta de acesso.",
                    "Protege contas e aparelhos."
                ), Difficulty.MEDIUM),
                level(78, 8, "ROBÔ", listOf(
                    "Máquina que pode executar tarefas.",
                    "Pode imitar movimentos humanos."
                ), Difficulty.HARD),
                level(79, 8, "IMPRESSORA", listOf(
                    "Coloca textos e imagens no papel.",
                    "Funciona com cartuchos de tinta."
                ), Difficulty.HARD),
                level(80, 8, "SISTEMA", listOf(
                    "Programa que organiza o computador.",
                    "O Android é um deles."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 9 — Além do Espaço 🚀
        chapter(
            9, "Além do Espaço",
            theme("Espaço", "🚀", 0xFF283593, 0xFF5E35B1, 0xFF29B6F6, 0xFFE8EAF6, 0xFFE1BEE7,
                listOf("🚀", "🪐", "⭐", "🌙", "🛸")),
            listOf(
                level(81, 9, "LUA", listOf(
                    "Satélite natural da Terra.",
                    "Brilha à noite."
                ), Difficulty.EASY),
                level(82, 9, "SOL", listOf(
                    "Estrela que dá luz e calor.",
                    "Nasce no leste."
                ), Difficulty.EASY),
                level(83, 9, "ESTRELA", listOf(
                    "Ponto brilhante no céu noturno.",
                    "Forma constelações."
                ), Difficulty.EASY),
                level(84, 9, "FOGUETE", listOf(
                    "Leva astronautas ao espaço.",
                    "Sobe para cima com fogo."
                ), Difficulty.EASY),
                level(85, 9, "PLANETA", listOf(
                    "Corpo que orbita uma estrela.",
                    "A Terra é um deles."
                ), Difficulty.MEDIUM),
                level(86, 9, "ASTEROIDE", listOf(
                    "Rocha que viaja no espaço.",
                    "Pode ter crateras."
                ), Difficulty.MEDIUM),
                level(87, 9, "COMETA", listOf(
                    "Corpo com cauda brilhante.",
                    "Passa perto do Sol de tempos em tempos."
                ), Difficulty.MEDIUM),
                level(88, 9, "SATÉLITE", listOf(
                    "Objeto que orbita um planeta.",
                    "Usado para comunicações."
                ), Difficulty.HARD),
                level(89, 9, "ÓRBITA", listOf(
                    "Caminho que um astro percorre.",
                    "O planeta a segue ao redor do Sol."
                ), Difficulty.HARD),
                level(90, 9, "NEBULOSA", listOf(
                    "Nuvem de gás e poeira no espaço.",
                    "Berço de novas estrelas."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 10 — Círculo do Mistério 🔮
        chapter(
            10, "Círculo do Mistério",
            theme("Mistério", "🔮", 0xFF6A1B9A, 0xFFF9A825, 0xFF4A148C, 0xFFF3E5F5, 0xFFFFF8E1,
                listOf("🔮", "🔍", "👁️", "🗝️", "🌙")),
            listOf(
                level(91, 10, "ENIGMA", listOf(
                    "Pergunta difícil de responder.",
                    "Também chamado de charada."
                ), Difficulty.EASY),
                level(92, 10, "MISTÉRIO", listOf(
                    "Algo que não conseguimos explicar.",
                    "Tema de histórias de detetive."
                ), Difficulty.EASY),
                level(93, 10, "PISTA", listOf(
                    "Indício que ajuda a resolver um caso.",
                    "O detetive a procura."
                ), Difficulty.EASY),
                level(94, 10, "SOMBRA", listOf(
                    "Área escura feita pela luz.",
                    "Segue a gente na rua."
                ), Difficulty.EASY),
                level(95, 10, "DETETIVE", listOf(
                    "Investiga crimes e mistérios.",
                    "Procura pistas e suspeitos."
                ), Difficulty.MEDIUM),
                level(96, 10, "LABIRINTO", listOf(
                    "Caminho cheio de passagens.",
                    "Fácil de se perder."
                ), Difficulty.MEDIUM),
                level(97, 10, "ALQUIMIA", listOf(
                    "Antiga prática de transformar metais.",
                    "Procurava a pedra filosofal."
                ), Difficulty.MEDIUM),
                level(98, 10, "FANTASMA", listOf(
                    "Espírito que assombra lugares.",
                    "Aparece em histórias de terror."
                ), Difficulty.HARD),
                level(99, 10, "PRESSÁGIO", listOf(
                    "Sinal que anuncia o futuro.",
                    "Pode ser bom ou ruim."
                ), Difficulty.HARD),
                level(100, 10, "DESAPARECIDO", listOf(
                    "Que sumiu sem deixar vestígios.",
                    "Uma pessoa que não é mais encontrada."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 11 — Na Estrada 🚗
        chapter(
            11, "Na Estrada",
            theme("Veículos", "🚗", 0xFFC62828, 0xFF1565C0, 0xFF00695C, 0xFFFFEBEE, 0xFFE3F2FD,
                listOf("🚗", "🏍️", "🚌", "🚑", "🚓")),
            listOf(
                level(101, 11, "CARRO", listOf(
                    "Veículo de quatro rodas.",
                    "Tem motor e volante."
                ), Difficulty.EASY),
                level(102, 11, "BICICLETA", listOf(
                    "Tem duas rodas e pedais.",
                    "Movida pela força das pernas."
                ), Difficulty.EASY),
                level(103, 11, "ÔNIBUS", listOf(
                    "Transporte que leva muitas pessoas.",
                    "Faz paradas nos pontos."
                ), Difficulty.EASY),
                level(104, 11, "AVIÃO", listOf(
                    "Voa pelos céus.",
                    "Leva passageiros para longe."
                ), Difficulty.EASY),
                level(105, 11, "MOTO", listOf(
                    "Veículo de duas rodas com motor.",
                    "Controlada pelo guidão."
                ), Difficulty.MEDIUM),
                level(106, 11, "TREM", listOf(
                    "Viaja sobre trilhos.",
                    "Transporta passageiros em vagões."
                ), Difficulty.MEDIUM),
                level(107, 11, "NAVIO", listOf(
                    "Grande embarcação no mar.",
                    "Navega por oceanos."
                ), Difficulty.MEDIUM),
                level(108, 11, "HELICÓPTERO", listOf(
                    "Voa com pás girando no topo.",
                    "Pode pousar e decolar na vertical."
                ), Difficulty.HARD),
                level(109, 11, "CAMINHÃO", listOf(
                    "Veículo grande para cargas.",
                    "Tem uma caçamba ou baú."
                ), Difficulty.HARD),
                level(110, 11, "SUBMARINO", listOf(
                    "Navega embaixo d'água.",
                    "Usado por marinheiros."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 12 — Meu Lar 🏠
        chapter(
            12, "Meu Lar",
            theme("Casa", "🏠", 0xFF8D6E63, 0xFF42A5F5, 0xFF66BB6A, 0xFFFBE9E7, 0xFFE3F2FD,
                listOf("🏠", "🛏️", "🛋️", "🚪", "🪟")),
            listOf(
                level(111, 12, "QUARTO", listOf(
                    "Cômodo onde dormimos.",
                    "Tem uma cama."
                ), Difficulty.EASY),
                level(112, 12, "COZINHA", listOf(
                    "Lugar onde se prepara comida.",
                    "Tem fogão e geladeira."
                ), Difficulty.EASY),
                level(113, 12, "BANHEIRO", listOf(
                    "Cômodo com chuveiro.",
                    "Onde tomamos banho."
                ), Difficulty.EASY),
                level(114, 12, "JANELA", listOf(
                    "Abre para ver o lado de fora.",
                    "Fica na parede."
                ), Difficulty.EASY),
                level(115, 12, "SOFÁ", listOf(
                    "Móvel para sentar e descansar.",
                    "Fica na sala."
                ), Difficulty.MEDIUM),
                level(116, 12, "GELADEIRA", listOf(
                    "Eletrodoméstico que conserva comida.",
                    "Mantém os alimentos frios."
                ), Difficulty.MEDIUM),
                level(117, 12, "CORTINA", listOf(
                    "Pano que cobre a janela.",
                    "Controla a entrada de luz."
                ), Difficulty.MEDIUM),
                level(118, 12, "TELHADO", listOf(
                    "Cobre o topo da casa.",
                    "Protege da chuva."
                ), Difficulty.HARD),
                level(119, 12, "ESCADARIA", listOf(
                    "Serve para subir andares.",
                    "Feita de degraus."
                ), Difficulty.HARD),
                level(120, 12, "VARANDA", listOf(
                    "Área aberta na frente ou fundo da casa.",
                    "Boa para colocar plantas."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 13 — Tempo de Férias 🏖️
        chapter(
            13, "Tempo de Férias",
            theme("Férias", "🏖️", 0xFF039BE5, 0xFFFDD835, 0xFFF4511E, 0xFFE1F5FE, 0xFFFFF9C4,
                listOf("🏖️", "🌴", "🕶️", "⛱️", "🏄")),
            listOf(
                level(121, 13, "MALA", listOf(
                    "Bagagem para viagens.",
                    "Guarda as roupas que levamos."
                ), Difficulty.EASY),
                level(122, 13, "CHAPÉU", listOf(
                    "Protege a cabeça do sol.",
                    "Usado na praia."
                ), Difficulty.EASY),
                level(123, 13, "PISCINA", listOf(
                    "Tanque com água para nadar.",
                    "Comum em hotéis."
                ), Difficulty.EASY),
                level(124, 13, "AREIA", listOf(
                    "Grão fino da praia.",
                    "Vira castelo com as mãos."
                ), Difficulty.EASY),
                level(125, 13, "OCEANO", listOf(
                    "Grande extensão de água salgada.",
                    "Maior que o mar."
                ), Difficulty.MEDIUM),
                level(126, 13, "SURFE", listOf(
                    "Esporte sobre a onda.",
                    "Usa uma prancha."
                ), Difficulty.MEDIUM),
                level(127, 13, "TOALHA", listOf(
                    "Pano usado para se secar.",
                    "Esticada na areia da praia."
                ), Difficulty.MEDIUM),
                level(128, 13, "PASSEIO", listOf(
                    "Caminhada para conhecer lugares.",
                    "Feito a pé ou de carro."
                ), Difficulty.HARD),
                level(129, 13, "CRUZEIRO", listOf(
                    "Viagem de navio.",
                    "Passa por vários portos."
                ), Difficulty.HARD),
                level(130, 13, "PARAQUEDAS", listOf(
                    "Pano que freia a queda no ar.",
                    "Usado em saltos de avião."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 14 — Arena dos Esportes ⚽
        chapter(
            14, "Arena dos Esportes",
            theme("Esportes", "⚽", 0xFF2E7D32, 0xFF1E88E5, 0xFFEF6C00, 0xFFE8F5E9, 0xFFE3F2FD,
                listOf("⚽", "🏀", "🏐", "🎾", "🏆")),
            listOf(
                level(131, 14, "BOLA", listOf(
                    "Usada em vários esportes.",
                    "Pode ser de futebol ou basquete."
                ), Difficulty.EASY),
                level(132, 14, "GOL", listOf(
                    "Ponto no futebol.",
                    "Quando a bola entra na rede."
                ), Difficulty.EASY),
                level(133, 14, "CORRIDA", listOf(
                    "Esporte de quem corre.",
                    "Quem chega primeiro vence."
                ), Difficulty.EASY),
                level(134, 14, "NATAÇÃO", listOf(
                    "Esporte na água.",
                    "Nadar é o objetivo."
                ), Difficulty.EASY),
                level(135, 14, "TORNEIO", listOf(
                    "Competição entre times.",
                    "Coroa o campeão."
                ), Difficulty.MEDIUM),
                level(136, 14, "VENCEDOR", listOf(
                    "Quem ganha a competição.",
                    "Recebe o troféu."
                ), Difficulty.MEDIUM),
                level(137, 14, "JUIZ", listOf(
                    "Aplica as regras do jogo.",
                    "Tem um apito."
                ), Difficulty.MEDIUM),
                level(138, 14, "ATLETA", listOf(
                    "Pessoa que pratica esportes.",
                    "Treina para competições."
                ), Difficulty.HARD),
                level(139, 14, "MEDALHA", listOf(
                    "Prêmio de metal dado ao atleta.",
                    "Pode ser de ouro, prata ou bronze."
                ), Difficulty.HARD),
                level(140, 14, "TREINADOR", listOf(
                    "Ensina e orienta o time.",
                    "Planeja os treinos."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 15 — Melodia Secreta 🎵
        chapter(
            15, "Melodia Secreta",
            theme("Música", "🎵", 0xFF8E24AA, 0xFFEC407A, 0xFF5E35B1, 0xFFF3E5F5, 0xFFFCE4EC,
                listOf("🎵", "🎸", "🎹", "🎤", "🥁")),
            listOf(
                level(141, 15, "MÚSICA", listOf(
                    "Sequência de sons agradáveis.",
                    "Ouvimos com os fones."
                ), Difficulty.EASY),
                level(142, 15, "CANÇÃO", listOf(
                    "Música com letra e voz.",
                    "Cantada por um cantor."
                ), Difficulty.EASY),
                level(143, 15, "GUITARRA", listOf(
                    "Instrumento com cordas e braço.",
                    "Usada no rock."
                ), Difficulty.EASY),
                level(144, 15, "BATERIA", listOf(
                    "Instrumento tocado com baquetas.",
                    "Feita de tambores."
                ), Difficulty.EASY),
                level(145, 15, "PIANO", listOf(
                    "Instrumento com teclas.",
                    "Tem teclas brancas e pretas."
                ), Difficulty.MEDIUM),
                level(146, 15, "CORO", listOf(
                    "Grupo de cantores juntos.",
                    "Canta em harmonia."
                ), Difficulty.MEDIUM),
                level(147, 15, "MELODIA", listOf(
                    "Sequência de notas.",
                    "Parte que fica na cabeça."
                ), Difficulty.MEDIUM),
                level(148, 15, "COMPOSITOR", listOf(
                    "Cria músicas.",
                    "Escreve partituras."
                ), Difficulty.HARD),
                level(149, 15, "ORQUESTRA", listOf(
                    "Grande grupo de músicos.",
                    "Tocam instrumentos juntos."
                ), Difficulty.HARD),
                level(150, 15, "RITMO", listOf(
                    "Batida de uma música.",
                    "Seguimos com o pé ou palmas."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 16 — Cenas de Cinema 🎬
        chapter(
            16, "Cenas de Cinema",
            theme("Cinema", "🎬", 0xFF37474F, 0xFFE53935, 0xFFFFCA28, 0xFFECEFF1, 0xFFFFEBEE,
                listOf("🎬", "🎥", "🍿", "🎞️", "🎭")),
            listOf(
                level(151, 16, "FILME", listOf(
                    "História em movimento na tela.",
                    "Assistimos no cinema."
                ), Difficulty.EASY),
                level(152, 16, "CENA", listOf(
                    "Parte de um filme.",
                    "Cada pedaço da história."
                ), Difficulty.EASY),
                level(153, 16, "CÂMERA", listOf(
                    "Equipamento que filma.",
                    "Capta imagens em vídeo."
                ), Difficulty.EASY),
                level(154, 16, "TELA", listOf(
                    "Superfície onde o filme aparece.",
                    "Branca e grande no cinema."
                ), Difficulty.EASY),
                level(155, 16, "ATOR", listOf(
                    "Pessoa que interpreta um papel.",
                    "Atua nos filmes."
                ), Difficulty.MEDIUM),
                level(156, 16, "DIRETOR", listOf(
                    "Comanda as filmagens.",
                    "Diz como cada cena deve ser."
                ), Difficulty.MEDIUM),
                level(157, 16, "ROTEIRO", listOf(
                    "Texto com a história do filme.",
                    "É escrito antes da filmagem."
                ), Difficulty.MEDIUM),
                level(158, 16, "CINEMA", listOf(
                    "Lugar onde assistimos filmes.",
                    "Tem uma tela gigante."
                ), Difficulty.HARD),
                level(159, 16, "PROTAGONISTA", listOf(
                    "Personagem principal da história.",
                    "É o herói do filme."
                ), Difficulty.HARD),
                level(160, 16, "TRILHA", listOf(
                    "Música de fundo do filme.",
                    "Acompanha as cenas."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 17 — Reino da Fantasia 🧙
        chapter(
            17, "Reino da Fantasia",
            theme("Fantasia", "🧙", 0xFF5E35B1, 0xFF1E88E5, 0xFF8D6E63, 0xFFEDE7F6, 0xFFE3F2FD,
                listOf("🧙", "🐉", "🦄", "✨", "⚔️")),
            listOf(
                level(161, 17, "DRAGÃO", listOf(
                    "Criatura que cospe fogo.",
                    "Tem asas e escamas."
                ), Difficulty.EASY),
                level(162, 17, "UNICÓRNIO", listOf(
                    "Cavalo com um chifre na testa.",
                    "Aparece em contos de fadas."
                ), Difficulty.EASY),
                level(163, 17, "MAGO", listOf(
                    "Faz mágicas e feitiços.",
                    "Usa chapéu e varinha."
                ), Difficulty.EASY),
                level(164, 17, "FADA", listOf(
                    "Criatura pequena com asas.",
                    "Concede desejos."
                ), Difficulty.EASY),
                level(165, 17, "CAVERNA", listOf(
                    "Buraco natural na montanha.",
                    "Esconde tesouros."
                ), Difficulty.MEDIUM),
                level(166, 17, "CASTELO", listOf(
                    "Casa de reis e rainhas.",
                    "Tem torres e muralhas."
                ), Difficulty.MEDIUM),
                level(167, 17, "VARINHA", listOf(
                    "Bastão que faz magia.",
                    "O mago usa para lançar feitiços."
                ), Difficulty.MEDIUM),
                level(168, 17, "ELFO", listOf(
                    "Criatura mágica com orelhas pontudas.",
                    "Vive em florestas."
                ), Difficulty.HARD),
                level(169, 17, "FEITIÇO", listOf(
                    "Encantamento feito por magia.",
                    "Pode ser lançado por um mago."
                ), Difficulty.HARD),
                level(170, 17, "REINO", listOf(
                    "Território governado por um rei.",
                    "Cheio de castelos e vilas."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 18 — Viagem no Tempo 🏰
        chapter(
            18, "Viagem no Tempo",
            theme("História", "🏰", 0xFF6D4C41, 0xFFC6A700, 0xFF3E2723, 0xFFEFEBE9, 0xFFFFF8E1,
                listOf("🏰", "⚔️", "👑", "📜", "🏛️")),
            listOf(
                level(171, 18, "REI", listOf(
                    "Governante de um reino.",
                    "Usa uma coroa."
                ), Difficulty.EASY),
                level(172, 18, "PRINCESA", listOf(
                    "Filha de um rei.",
                    "Vive em um castelo."
                ), Difficulty.EASY),
                level(173, 18, "COROA", listOf(
                    "Adorno de ouro na cabeça do rei.",
                    "Símbolo de realeza."
                ), Difficulty.EASY),
                level(174, 18, "ESPADA", listOf(
                    "Arma de lâmina.",
                    "Usada por cavaleiros."
                ), Difficulty.EASY),
                level(175, 18, "ESCUDO", listOf(
                    "Protege contra golpes.",
                    "Levado pelo cavaleiro."
                ), Difficulty.MEDIUM),
                level(176, 18, "CAVALEIRO", listOf(
                    "Guerreiro montado a cavalo.",
                    "Usa armadura."
                ), Difficulty.MEDIUM),
                level(177, 18, "ARCO", listOf(
                    "Arma que lança flechas.",
                    "Puxado com uma corda."
                ), Difficulty.MEDIUM),
                level(178, 18, "ARMADURA", listOf(
                    "Veste de metal do guerreiro.",
                    "Protege o corpo na batalha."
                ), Difficulty.HARD),
                level(179, 18, "BATALHA", listOf(
                    "Luta entre exércitos.",
                    "Acontece na guerra."
                ), Difficulty.HARD),
                level(180, 18, "IMPÉRIO", listOf(
                    "Grande reino governado por um imperador.",
                    "Conquista muitos territórios."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 19 — Segredos do Oceano 🌊
        chapter(
            19, "Segredos do Oceano",
            theme("Oceano", "🌊", 0xFF0277BD, 0xFF26A69A, 0xFF00BCD4, 0xFFE1F5FE, 0xFFB2DFDB,
                listOf("🌊", "🐬", "🐠", "🐙", "🦈")),
            listOf(
                level(181, 19, "PEIXE", listOf(
                    "Animal que vive na água.",
                    "Tem barbatanas e escamas."
                ), Difficulty.EASY),
                level(182, 19, "ONDA", listOf(
                    "Movimento da água do mar.",
                    "Quebra na praia."
                ), Difficulty.EASY),
                level(183, 19, "CONCHA", listOf(
                    "Casca dura de um molusco.",
                    "Encontrada na areia."
                ), Difficulty.EASY),
                level(184, 19, "POLVO", listOf(
                    "Tem oito tentáculos.",
                    "Solta tinta para se esconder."
                ), Difficulty.EASY),
                level(185, 19, "CORAL", listOf(
                    "Estrutura colorida no fundo do mar.",
                    "Forma recifes."
                ), Difficulty.MEDIUM),
                level(186, 19, "MEDUSA", listOf(
                    "Animal gelatinoso que pode picar.",
                    "Flutua na água."
                ), Difficulty.MEDIUM),
                level(187, 19, "ALGA", listOf(
                    "Planta que vive no mar.",
                    "Verde e escorregadia."
                ), Difficulty.MEDIUM),
                level(188, 19, "ESTRELA-DO-MAR", listOf(
                    "Animal marinho em forma de estrela.",
                    "Tem cinco pontas."
                ), Difficulty.HARD),
                level(189, 19, "TARTARUGA", listOf(
                    "Animal de casco que nada no mar.",
                    "Vive muitos anos."
                ), Difficulty.HARD),
                level(190, 19, "LAGOSTA", listOf(
                    "Crustáceo com garras.",
                    "Vive no fundo do mar."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 20 — Terra dos Dinossauros 🦖
        chapter(
            20, "Terra dos Dinossauros",
            theme("Dinossauros", "🦖", 0xFF558B2F, 0xFFEF6C00, 0xFF5D4037, 0xFFF1F8E9, 0xFFFFE0B2,
                listOf("🦖", "🦕", "🌋", "🦴", "⛰️")),
            listOf(
                level(191, 20, "FÓSSIL", listOf(
                    "Resto de dinossauro preservado em rocha.",
                    "Estudado pelos paleontólogos."
                ), Difficulty.EASY),
                level(192, 20, "OSSO", listOf(
                    "Parte dura do esqueleto.",
                    "Fica dentro do corpo."
                ), Difficulty.EASY),
                level(193, 20, "LAGARTO", listOf(
                    "Réptil que rasteja.",
                    "Parece um mini dinossauro."
                ), Difficulty.EASY),
                level(194, 20, "OVO", listOf(
                    "Os dinossauros nasciam dele.",
                    "Eclode quando aquecido."
                ), Difficulty.EASY),
                level(195, 20, "DENTE", listOf(
                    "Parte dura para morder.",
                    "O Tiranossauro tinha os enormes."
                ), Difficulty.MEDIUM),
                level(196, 20, "PATADA", listOf(
                    "Passo forte de um animal.",
                    "Deixa marcas no chão."
                ), Difficulty.MEDIUM),
                level(197, 20, "JURÁSSICO", listOf(
                    "Período dos dinossauros.",
                    "Época em que eles viveram."
                ), Difficulty.MEDIUM),
                level(198, 20, "CARNÍVORO", listOf(
                    "Que se alimenta de carne.",
                    "O T-Rex era um deles."
                ), Difficulty.HARD),
                level(199, 20, "HERBÍVORO", listOf(
                    "Que se alimenta de plantas.",
                    "O brontossauro era um deles."
                ), Difficulty.HARD),
                level(200, 20, "PALEONTÓLOGO", listOf(
                    "Cientista que estuda fósseis.",
                    "Procura ossos de dinossauros."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 21 — Grande Aventura 🌋
        chapter(
            21, "Grande Aventura",
            theme("Aventura", "🌋", 0xFFD84315, 0xFFFDD835, 0xFF6D4C41, 0xFFFBE9E7, 0xFFFFF9C4,
                listOf("🌋", "🏔️", "⛺", "🧗", "🗺️")),
            listOf(
                level(201, 21, "MAPA", listOf(
                    "Guia de caminhos e lugares.",
                    "Mostra como chegar ao destino."
                ), Difficulty.EASY),
                level(202, 21, "BÚSSOLA", listOf(
                    "Aponta o norte.",
                    "Ajuda a se orientar."
                ), Difficulty.EASY),
                level(203, 21, "MOCHILA", listOf(
                    "Bolsa carregada nas costas.",
                    "Leva itens na trilha."
                ), Difficulty.EASY),
                level(204, 21, "TENDA", listOf(
                    "Abrigo desmontável para acampar.",
                    "Montada no acampamento."
                ), Difficulty.EASY),
                level(205, 21, "TRILHA", listOf(
                    "Caminho na natureza.",
                    "Seguimos a pé."
                ), Difficulty.MEDIUM),
                level(206, 21, "EXPLORADOR", listOf(
                    "Quem viaja para descobrir lugares.",
                    "Usa bússola e mapa."
                ), Difficulty.MEDIUM),
                level(207, 21, "AVENTURA", listOf(
                    "Viagem cheia de desafios.",
                    "História de exploração."
                ), Difficulty.MEDIUM),
                level(208, 21, "MONTANHA", listOf(
                    "Grande elevação de terra.",
                    "Escalada por aventureiros."
                ), Difficulty.HARD),
                level(209, 21, "ACAMPAMENTO", listOf(
                    "Lugar onde se monta acampamento.",
                    "Rodeado de natureza."
                ), Difficulty.HARD),
                level(210, 21, "SOBREVIVÊNCIA", listOf(
                    "Capacidade de se manter vivo.",
                    "Exige abrigo, água e comida."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 22 — Laboratório Curioso 🧪
        chapter(
            22, "Laboratório Curioso",
            theme("Ciência", "🧪", 0xFF00ACC1, 0xFF7E57C2, 0xFF26C6DA, 0xFFE0F7FA, 0xFFEDE7F6,
                listOf("🧪", "🔬", "⚗️", "🧬", "🔭")),
            listOf(
                level(211, 22, "ÁTOMO", listOf(
                    "Unidade básica da matéria.",
                    "Forma tudo o que existe."
                ), Difficulty.EASY),
                level(212, 22, "ENERGIA", listOf(
                    "Força que move as coisas.",
                    "Pode vir do sol."
                ), Difficulty.EASY),
                level(213, 22, "LUPA", listOf(
                    "Lente que aumenta as coisas.",
                    "Usada para observar de perto."
                ), Difficulty.EASY),
                level(214, 22, "LABORATÓRIO", listOf(
                    "Lugar onde se faz experimentos.",
                    "Cheio de vidros e substâncias."
                ), Difficulty.EASY),
                level(215, 22, "EXPERIMENTO", listOf(
                    "Teste feito para descobrir algo.",
                    "Acontece no laboratório."
                ), Difficulty.MEDIUM),
                level(216, 22, "MOLÉCULA", listOf(
                    "União de átomos.",
                    "Forma uma substância."
                ), Difficulty.MEDIUM),
                level(217, 22, "MAGNETO", listOf(
                    "Atrai metais.",
                    "Tem polos norte e sul."
                ), Difficulty.MEDIUM),
                level(218, 22, "DESCOBERTA", listOf(
                    "Algo novo que se encontra.",
                    "Avança a ciência."
                ), Difficulty.HARD),
                level(219, 22, "HIPÓTESE", listOf(
                    "Suposição a ser testada.",
                    "Vem antes do experimento."
                ), Difficulty.HARD),
                level(220, 22, "CIENTISTA", listOf(
                    "Pessoa que faz ciência.",
                    "Pesquisa e testa ideias."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 23 — Mundo dos Jogos 🧩
        chapter(
            23, "Mundo dos Jogos",
            theme("Jogos", "🧩", 0xFF1E88E5, 0xFFFDD835, 0xFFEF6C00, 0xFFE3F2FD, 0xFFFFF9C4,
                listOf("🧩", "🎲", "🎮", "🃏", "♟️")),
            listOf(
                level(221, 23, "DADO", listOf(
                    "Cubo com números.",
                    "Usado em jogos de tabuleiro."
                ), Difficulty.EASY),
                level(222, 23, "CARTA", listOf(
                    "Papel retangular de um baralho.",
                    "Vem em naipes e números."
                ), Difficulty.EASY),
                level(223, 23, "PEÇA", listOf(
                    "Item usado em jogos.",
                    "Move-se no tabuleiro."
                ), Difficulty.EASY),
                level(224, 23, "TABULEIRO", listOf(
                    "Superfície de um jogo.",
                    "Tem casas e caminhos."
                ), Difficulty.EASY),
                level(225, 23, "XADREZ", listOf(
                    "Jogo com reis e peões.",
                    "Jogado em um tabuleiro quadriculado."
                ), Difficulty.MEDIUM),
                level(226, 23, "PUZZLE", listOf(
                    "Quebra-cabeça de encaixe.",
                    "Monta-se uma figura."
                ), Difficulty.MEDIUM),
                level(227, 23, "TURNO", listOf(
                    "Vez de jogar.",
                    "Cada jogador tem a sua."
                ), Difficulty.MEDIUM),
                level(228, 23, "ESTRATÉGIA", listOf(
                    "Plano para vencer.",
                    "Pensada antes da jogada."
                ), Difficulty.HARD),
                level(229, 23, "ADVERSÁRIO", listOf(
                    "Oponente no jogo.",
                    "Contra quem você joga."
                ), Difficulty.HARD),
                level(230, 23, "CAMPEÃO", listOf(
                    "Vencedor de um torneio.",
                    "Leva o troféu."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 24 — Coração da Cidade 🏙️
        chapter(
            24, "Coração da Cidade",
            theme("Cidade", "🏙️", 0xFF546E7A, 0xFF1E88E5, 0xFF66BB6A, 0xFFECEFF1, 0xFFE3F2FD,
                listOf("🏙️", "🏢", "🚦", "🌉", "🚕")),
            listOf(
                level(231, 24, "RUA", listOf(
                    "Caminho entre prédios.",
                    "Por onde passam carros."
                ), Difficulty.EASY),
                level(232, 24, "PRÉDIO", listOf(
                    "Edifício alto.",
                    "Tem muitos andares."
                ), Difficulty.EASY),
                level(233, 24, "SEMÁFORO", listOf(
                    "Controla o trânsito.",
                    "Tem luzes vermelha, amarela e verde."
                ), Difficulty.EASY),
                level(234, 24, "LOJA", listOf(
                    "Lugar onde se compra.",
                    "Tem vitrines."
                ), Difficulty.EASY),
                level(235, 24, "PONTE", listOf(
                    "Passa por cima de um rio.",
                    "Une dois lados."
                ), Difficulty.MEDIUM),
                level(236, 24, "CALÇADA", listOf(
                    "Passeio para pedestres.",
                    "Fica ao lado da rua."
                ), Difficulty.MEDIUM),
                level(237, 24, "VIADUTO", listOf(
                    "Passagem por cima de ruas.",
                    "Evita cruzamentos."
                ), Difficulty.MEDIUM),
                level(238, 24, "TRÂNSITO", listOf(
                    "Conjunto de veículos nas ruas.",
                    "Pode ficar engarrafado."
                ), Difficulty.HARD),
                level(239, 24, "PEDESTRE", listOf(
                    "Quem anda a pé.",
                    "Usa a calçada."
                ), Difficulty.HARD),
                level(240, 24, "SKATE", listOf(
                    "Prancha com rodas.",
                    "Anda pela cidade."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 25 — Volta ao Mundo 🌎
        chapter(
            25, "Volta ao Mundo",
            theme("Mundo", "🌎", 0xFF1976D2, 0xFF43A047, 0xFFF57F17, 0xFFE3F2FD, 0xFFE8F5E9,
                listOf("🌎", "🗺️", "🧭", "✈️", "🌍")),
            listOf(
                level(241, 25, "GLOBO", listOf(
                    "Representação redonda da Terra.",
                    "Gira sobre um eixo."
                ), Difficulty.EASY),
                level(242, 25, "CONTINENTE", listOf(
                    "Grande extensão de terra.",
                    "A América é um deles."
                ), Difficulty.EASY),
                level(243, 25, "FRONTEIRA", listOf(
                    "Limite entre países.",
                    "Pode ser uma linha imaginária."
                ), Difficulty.EASY),
                level(244, 25, "PAÍS", listOf(
                    "Território com governo.",
                    "O Brasil é um deles."
                ), Difficulty.EASY),
                level(245, 25, "IDIOMA", listOf(
                    "Língua falada por um povo.",
                    "O português é um deles."
                ), Difficulty.MEDIUM),
                level(246, 25, "VIAJANTE", listOf(
                    "Pessoa que percorre o mundo.",
                    "Conhece vários países."
                ), Difficulty.MEDIUM),
                level(247, 25, "BANDEIRA", listOf(
                    "Símbolo de uma nação.",
                    "Tem cores e desenhos."
                ), Difficulty.MEDIUM),
                level(248, 25, "CULTURA", listOf(
                    "Costumes de um povo.",
                    "Inclui música, comida e arte."
                ), Difficulty.HARD),
                level(249, 25, "MERIDIANO", listOf(
                    "Linha imaginária da Terra.",
                    "Divide leste e oeste."
                ), Difficulty.HARD),
                level(250, 25, "ORIENTE", listOf(
                    "Direção onde o sol nasce.",
                    "Oposto do ocidente."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 26 — Grandes Desafios 🏆
        chapter(
            26, "Grandes Desafios",
            theme("Desafios", "🏆", 0xFFF9A825, 0xFFC62828, 0xFF37474F, 0xFFFFF8E1, 0xFFFFEBEE,
                listOf("🏆", "🥇", "💪", "🔥", "🎯")),
            listOf(
                level(251, 26, "META", listOf(
                    "Objetivo a alcançar.",
                    "Queremos atingir."
                ), Difficulty.EASY),
                level(252, 26, "ESFORÇO", listOf(
                    "Empenho para vencer.",
                    "Exige força e vontade."
                ), Difficulty.EASY),
                level(253, 26, "TROFÉU", listOf(
                    "Prêmio de campeão.",
                    "Levantado em vitória."
                ), Difficulty.EASY),
                level(254, 26, "TREINO", listOf(
                    "Preparo para melhorar.",
                    "Repetimos para evoluir."
                ), Difficulty.EASY),
                level(255, 26, "CORAGEM", listOf(
                    "Bravura para enfrentar.",
                    "Necessária em desafios."
                ), Difficulty.MEDIUM),
                level(256, 26, "RECORDE", listOf(
                    "Melhor marca já alcançada.",
                    "Pode ser quebrado."
                ), Difficulty.MEDIUM),
                level(257, 26, "PERSEVERANÇA", listOf(
                    "Não desistir mesmo com dificuldades.",
                    "Continuar tentando."
                ), Difficulty.MEDIUM),
                level(258, 26, "VITÓRIA", listOf(
                    "Vença o desafio.",
                    "Momento de comemoração."
                ), Difficulty.HARD),
                level(259, 26, "RESILIENTE", listOf(
                    "Que se recupera dos problemas.",
                    "Não desiste fácil."
                ), Difficulty.HARD),
                level(260, 26, "CONQUISTA", listOf(
                    "Algo alcançado com esforço.",
                    "Resultado de uma vitória."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 27 — Farol do Conhecimento 🧠
        chapter(
            27, "Farol do Conhecimento",
            theme("Conhecimento", "🧠", 0xFF1565C0, 0xFF6A1B9A, 0xFF00897B, 0xFFE3F2FD, 0xFFF3E5F5,
                listOf("🧠", "📖", "💡", "🔎", "🎓")),
            listOf(
                level(261, 27, "SABER", listOf(
                    "Conhecer algo.",
                    "Adquirimos ao estudar."
                ), Difficulty.EASY),
                level(262, 27, "LEITURA", listOf(
                    "Ato de ler.",
                    "Boa para aprender."
                ), Difficulty.EASY),
                level(263, 27, "ESTUDO", listOf(
                    "Prática de aprender.",
                    "Feito nos livros."
                ), Difficulty.EASY),
                level(264, 27, "LIÇÃO", listOf(
                    "Aprendizado dado pelo professor.",
                    "Cada capítulo de estudo."
                ), Difficulty.EASY),
                level(265, 27, "ALFABETO", listOf(
                    "Conjunto de letras.",
                    "Base da escrita."
                ), Difficulty.MEDIUM),
                level(266, 27, "CURIOSIDADE", listOf(
                    "Vontade de saber.",
                    "Impulsiona a ciência."
                ), Difficulty.MEDIUM),
                level(267, 27, "INTELIGÊNCIA", listOf(
                    "Capacidade de entender.",
                    "Usada para resolver problemas."
                ), Difficulty.MEDIUM),
                level(268, 27, "FILOSOFIA", listOf(
                    "Estudo do pensamento.",
                    "Faz perguntas profundas."
                ), Difficulty.HARD),
                level(269, 27, "ENCICLOPÉDIA", listOf(
                    "Livro com muitos assuntos.",
                    "Serve para consultas."
                ), Difficulty.HARD),
                level(270, 27, "APRENDIZADO", listOf(
                    "Processo de adquirir saber.",
                    "Resultado do estudo."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 28 — Lendas Assustadoras 👻
        chapter(
            28, "Lendas Assustadoras",
            theme("Sobrenatural", "👻", 0xFF4A148C, 0xFF37474F, 0xFF7B1FA2, 0xFFEDE7F6, 0xFFECEFF1,
                listOf("👻", "🕷️", "🌕", "🦇", "🔮")),
            listOf(
                level(271, 28, "LUAR", listOf(
                    "Luz da lua.",
                    "Brilha na noite."
                ), Difficulty.EASY),
                level(272, 28, "VELA", listOf(
                    "Acende luz com chama.",
                    "Cera que queima."
                ), Difficulty.EASY),
                level(273, 28, "TEIA", listOf(
                    "Fina rede de aranha.",
                    "Presa em cantos escuros."
                ), Difficulty.EASY),
                level(274, 28, "CORUJA", listOf(
                    "Ave da noite.",
                    "Pia no escuro."
                ), Difficulty.EASY),
                level(275, 28, "SEGREDO", listOf(
                    "Algo escondido.",
                    "Não contado aos outros."
                ), Difficulty.MEDIUM),
                level(276, 28, "ARANHA", listOf(
                    "Animal de oito patas.",
                    "Faz teias."
                ), Difficulty.MEDIUM),
                level(277, 28, "ESQUELETO", listOf(
                    "Conjunto de ossos.",
                    "Fica dentro do corpo."
                ), Difficulty.MEDIUM),
                level(278, 28, "LOBISOMEM", listOf(
                    "Vira lobo na lua cheia.",
                    "Criatura assustadora."
                ), Difficulty.HARD),
                level(279, 28, "FEITICEIRA", listOf(
                    "Mulher que faz magia.",
                    "Voava em uma vassoura."
                ), Difficulty.HARD),
                level(280, 28, "PESADELO", listOf(
                    "Sonho ruim.",
                    "Dá medo ao dormir."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 29 — Caçada ao Tesouro 💎
        chapter(
            29, "Caçada ao Tesouro",
            theme("Tesouros", "💎", 0xFF01579B, 0xFFC6A700, 0xFF00897B, 0xFFE1F5FE, 0xFFFFF8E1,
                listOf("💎", "👑", "💰", "🏴‍☠️", "🔱")),
            listOf(
                level(281, 29, "OURO", listOf(
                    "Metal amarelo valioso.",
                    "Procurado por piratas."
                ), Difficulty.EASY),
                level(282, 29, "BAÚ", listOf(
                    "Caixa de madeira de tesouro.",
                    "Guarda moedas e joias."
                ), Difficulty.EASY),
                level(283, 29, "MOEDA", listOf(
                    "Dinheiro de metal.",
                    "Tem cara e coroa."
                ), Difficulty.EASY),
                level(284, 29, "PEDRA", listOf(
                    "Rocha pequena.",
                    "Pode ser preciosa."
                ), Difficulty.EASY),
                level(285, 29, "DIAMANTE", listOf(
                    "Pedra preciosa brilhante.",
                    "A mais dura das pedras."
                ), Difficulty.MEDIUM),
                level(286, 29, "PIRATA", listOf(
                    "Procura tesouros nos mares.",
                    "Usa tapa-olho."
                ), Difficulty.MEDIUM),
                level(287, 29, "ILHA", listOf(
                    "Terra cercada de água.",
                    "Esconde tesouros em mapas."
                ), Difficulty.MEDIUM),
                level(288, 29, "ESFINGE", listOf(
                    "Guardiã misteriosa com enigma.",
                    "Protege tesouros antigos."
                ), Difficulty.HARD),
                level(289, 29, "TESOURO", listOf(
                    "Grande riqueza escondida.",
                    "Procurado em aventuras."
                ), Difficulty.HARD),
                level(290, 29, "JOIA", listOf(
                    "Peça de ouro e pedras.",
                    "Enfeitada e valiosa."
                ), Difficulty.HARD)
            )
        ),
        // Capítulo 30 — O Grande Mistério 👑
        chapter(
            30, "O Grande Mistério",
            theme("Grande Mistério", "👑", 0xFF6A1B9A, 0xFFF9A825, 0xFF880E4F, 0xFFF3E5F5, 0xFFFFF8E1,
                listOf("👑", "🔮", "🌟", "✨", "🗝️")),
            listOf(
                level(291, 30, "CHARADA", listOf(
                    "Pergunta de adivinhar.",
                    "Tem dicas escondidas."
                ), Difficulty.EASY),
                level(292, 30, "CÓDIGO", listOf(
                    "Símbolos que guardam um segredo.",
                    "Precisa ser decifrado."
                ), Difficulty.EASY),
                level(293, 30, "DISFARCE", listOf(
                    "Muda a aparência para enganar.",
                    "Usado para não ser reconhecido."
                ), Difficulty.EASY),
                level(294, 30, "VESTÍGIO", listOf(
                    "Marca deixada por algo.",
                    "Ajuda a resolver o mistério."
                ), Difficulty.EASY),
                level(295, 30, "TEORIA", listOf(
                    "Explicação ainda não confirmada.",
                    "Feita para entender o caso."
                ), Difficulty.MEDIUM),
                level(296, 30, "TESTEMUNHA", listOf(
                    "Pessoa que viu o ocorrido.",
                    "Conta o que viu."
                ), Difficulty.MEDIUM),
                level(297, 30, "DECIFRAR", listOf(
                    "Descobrir o sentido de um código.",
                    "Resolver o segredo."
                ), Difficulty.MEDIUM),
                level(298, 30, "MÁGICA", listOf(
                    "Truque que engana os olhos.",
                    "Feito por um ilusionista."
                ), Difficulty.HARD),
                level(299, 30, "REVELAÇÃO", listOf(
                    "Descoberta de algo escondido.",
                    "Momento em que o segredo é contado."
                ), Difficulty.HARD),
                level(300, 30, "MISTÉRIO", listOf(
                    "Algo sem explicação.",
                    "O enigma final do jogo."
                ), Difficulty.HARD)
            )
        )
    )

    /**
     * Todos os níveis em ordem global de progressão (capítulo 1 → 2 → ...),
     * derivada da coleção — nunca de números hardcoded.
     */
    val allLevels: List<Level> = chapters.flatMap { it.levels }.sortedBy { it.id }

    fun chapterById(id: Int): Chapter? = chapters.firstOrNull { it.id == id }

    fun levelById(id: Int): Level? = allLevels.firstOrNull { it.id == id }
}