package com.afonsomarques.myrefritech

data class DiagnosticQuestion(
    val question: String,
    val answers: List<String>
)

data class DiagnosticCheck(
    val title: String,
    val description: String,
    val recommendedActions: List<String>
)

enum class DiagnosticType {
    MOTORS_INOPERATIVE,
    MOTORS_LOW_ROTATION
}

data class DiagnosticInvestigation(
    val type: DiagnosticType,
    val causeName: String,
    val initialQuestion: DiagnosticQuestion
)

val motorsInoperativeInvestigation = DiagnosticInvestigation(
    type = DiagnosticType.MOTORS_INOPERATIVE,
    causeName = "Motores de ventilação inoperantes",
    initialQuestion = DiagnosticQuestion(
        question = "Todos os motores estão inoperantes?",
        answers = listOf("Sim", "Não")
    )
)

val initialMotorAnswers = listOf(
    DiagnosticAnswer(
        text = "Sim",
        nextStep = "GENERAL_POWER_CHECK"
    ),
    DiagnosticAnswer(
        text = "Não",
        nextStep = "PARTIAL_FAILURE_QUESTION"
    )
)

data class DiagnosticAnswer(
    val text: String,
    val nextStep: String
)

val partialFailureQuestion = DiagnosticQuestion(
    question = "Um ou mais motores estão inoperantes?",
    answers = listOf("Sim", "Não")
)

val partialFailureAnswers = listOf(
    DiagnosticAnswer(
        text = "Sim",
        nextStep = "INDIVIDUAL_MOTOR_CHECK"
    ),
    DiagnosticAnswer(
        text = "Não",
        nextStep = "SUGGEST_LOW_ROTATION"
    )
)

val generalPowerChecks = listOf(

    DiagnosticCheck(
        title = "Plugue principal de alimentação",
        description = "Verifique os terminais elétricos do plugue principal de alimentação do chicote dos motores.",
        recommendedActions = listOf(
            "Corrigir conexões com mau contato.",
            "Substituir terminais danificados.",
            "Verificar a integridade do plugue e do chicote de alimentação."
        )
    ),

    DiagnosticCheck(
        title = "Contatora de alimentação",
        description = "Se houver contatora, verifique seu funcionamento, a tensão de entrada e saída nos contatos de potência e se a tensão de alimentação está dentro do padrão.",
        recommendedActions = listOf(
            "Verificar possíveis falhas nos contatos de potência.",
            "Avaliar a condição da contatora e considerar sua substituição se confirmada a avaria.",
            "Verificar se a tensão de alimentação está dentro das especificações."
        )
    ),

    DiagnosticCheck(
        title = "Circuito de comando da contatora",
        description = "Se a contatora não estiver operando, verifique a bobina de acionamento, bornes, terminais, aperto das conexões e, quando disponível, consulte o diagrama elétrico para identificar possíveis alterações no circuito de comando.",
        recommendedActions = listOf(
            "Verificar a integridade da bobina de acionamento.",
            "Corrigir conexões frouxas ou terminais comprometidos.",
            "Investigar possíveis interrupções ou alterações no circuito de comando."
        )
    ),

    DiagnosticCheck(
        title = "Disjuntor de alimentação",
        description = "Na ausência de alimentação elétrica, verifique o estado do disjuntor.",
        recommendedActions = listOf(
            "Investigar a causa do desligamento do disjuntor.",
            "Verificar possíveis curtos-circuitos ou sobrecargas.",
            "Avaliar a necessidade de substituição do disjuntor, caso seja confirmada sua avaria."
        )
    ),

    DiagnosticCheck(
        title = "Saída digital do controlador",
        description = "Quando aplicável, verifique o funcionamento da saída digital do controlador responsável pelo acionamento da ventilação.",
        recommendedActions = listOf(
            "Conferir a configuração da saída digital.",
            "Verificar a presença do comando de acionamento.",
            "Investigar possível falha na saída do controlador."
        )
    ),

    DiagnosticCheck(
        title = "Emendas nos condutores",
        description = "Verifique a existência de emendas nos condutores de alimentação e suas condições, pois emendas mal executadas podem provocar falhas.",
        recommendedActions = listOf(
            "Refazer emendas com conexões inadequadas.",
            "Substituir trechos de condutores danificados.",
            "Garantir isolamento e conexão elétrica adequados."
        )
    ),

    DiagnosticCheck(
        title = "Continuidade do circuito",
        description = "Se necessário, realize testes de continuidade nos plugues, conectores e dispositivos de acionamento que possam estar interrompendo o circuito de alimentação.",
        recommendedActions = listOf(
            "Localizar o ponto de interrupção do circuito.",
            "Corrigir conexões ou condutores interrompidos.",
            "Substituir componentes que apresentem falha confirmada."
        )
    )
)

val individualMotorChecks = listOf(

    DiagnosticCheck(
        title = "Plugue conector do micromotor",
        description = "Verifique a integridade do plugue conector individual do micromotor, observando possíveis danos, mau contato ou terminais comprometidos.",
        recommendedActions = listOf(
            "Corrigir possíveis problemas de contato.",
            "Substituir terminais danificados.",
            "Substituir o conector caso apresente danos que comprometam seu funcionamento."
        )
    ),

    DiagnosticCheck(
        title = "Condutor de alimentação",
        description = "Verifique o estado do condutor de alimentação do micromotor, procurando possíveis rompimentos ou interrupções no circuito.",
        recommendedActions = listOf(
            "Localizar o ponto de rompimento do condutor.",
            "Corrigir a interrupção do circuito.",
            "Substituir trechos de condutores danificados, quando necessário."
        )
    ),

    DiagnosticCheck(
        title = "Suporte de sustentação e hélice",
        description = "Verifique se existe quebra ou deslocamento do suporte de sustentação, provocando o travamento da hélice e impedindo a rotação do motor.",
        recommendedActions = listOf(
            "Corrigir ou substituir o suporte de sustentação danificado.",
            "Verificar se a hélice apresenta danos.",
            "Garantir que a hélice possa girar livremente, sem interferências mecânicas."
        )
    ),

    DiagnosticCheck(
        title = "Condição do micromotor",
        description = "Se a alimentação elétrica estiver chegando normalmente e o conector estiver em boas condições, considere a possibilidade de avaria do micromotor.",
        recommendedActions = listOf(
            "Confirmar a avaria do micromotor por meio de testes adequados.",
            "Substituir o micromotor avariado por outro compatível com as especificações do equipamento."
        )
    )
)

data class DiagnosticResult(
    val title: String,
    val description: String,
    val recommendedSolution: String
)

val faultyMotorResult = DiagnosticResult(
    title = "Micromotor avariado",
    description = "A alimentação elétrica está chegando normalmente, o conector está em boas condições, mas o micromotor não apresenta funcionamento.",
    recommendedSolution = "Substituir o micromotor avariado por outro compatível com as especificações do equipamento."
)