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

val evaporatorIceChecks = listOf(

    DiagnosticCheck(
        title = "Falha no sistema de degelo",
        description = "Verifique se o degelo está ocorrendo corretamente e conforme a programação estabelecida.",
        recommendedActions = listOf(
            "Verificar a programação e os horários de degelo.",
            "Investigar possíveis falhas no acionamento do sistema.",
            "Corrigir irregularidades identificadas no ciclo de degelo."
        )
    ),

    DiagnosticCheck(
        title = "Resistências de degelo",
        description = "Quando aplicável, verifique a continuidade e o funcionamento das resistências de degelo.",
        recommendedActions = listOf(
            "Verificar a integridade elétrica das resistências.",
            "Corrigir possíveis problemas nas conexões elétricas.",
            "Substituir resistências que apresentem avaria confirmada."
        )
    ),

    DiagnosticCheck(
        title = "Sensor de degelo",
        description = "Verifique o posicionamento, a integridade e a leitura do sensor responsável pelo controle do degelo.",
        recommendedActions = listOf(
            "Corrigir o posicionamento do sensor.",
            "Verificar a integridade dos cabos e conexões.",
            "Substituir o sensor caso apresente falha confirmada."
        )
    ),

    DiagnosticCheck(
        title = "Controlador eletrônico",
        description = "Confira os parâmetros e a programação do controlador responsáveis pelo funcionamento do degelo.",
        recommendedActions = listOf(
            "Corrigir parâmetros de degelo configurados incorretamente.",
            "Verificar o funcionamento das saídas de acionamento.",
            "Investigar possíveis falhas no controlador."
        )
    ),

    DiagnosticCheck(
        title = "Dreno do evaporador",
        description = "Verifique se existem obstruções que impeçam o escoamento adequado da água proveniente do degelo.",
        recommendedActions = listOf(
            "Realizar a limpeza do dreno.",
            "Remover obstruções que impeçam o escoamento.",
            "Verificar o correto direcionamento e escoamento da água."
        )
    ),

    DiagnosticCheck(
        title = "Ventiladores do evaporador",
        description = "Verifique o funcionamento dos ventiladores e possíveis interferências que comprometam a circulação de ar.",
        recommendedActions = listOf(
            "Investigar falhas elétricas ou mecânicas nos ventiladores.",
            "Corrigir interferências que impeçam a ventilação.",
            "Substituir componentes avariados, quando necessário."
        )
    ),

    DiagnosticCheck(
        title = "Vedação e infiltração de ar",
        description = "Verifique possíveis falhas de vedação e entradas excessivas de ar e umidade no ambiente refrigerado.",
        recommendedActions = listOf(
            "Corrigir falhas nas vedações.",
            "Substituir guarnições danificadas.",
            "Investigar possíveis pontos de infiltração de ar."
        )
    ),

    DiagnosticCheck(
        title = "Setpoint de temperatura e histerese",
        description = "Verifique se o setpoint não está excessivamente baixo e se a histerese está acima de 2 °C, considerando as especificações do equipamento.",
        recommendedActions = listOf(
            "Adequar o setpoint às condições de operação do equipamento.",
            "Ajustar a histerese conforme as recomendações do fabricante.",
            "Verificar se os parâmetros configurados estão coerentes com a aplicação."
        )
    ),

    DiagnosticCheck(
        title = "Defletor ou direcionador de ar",
        description = "Verifique se o defletor ou direcionador de ar acima do evaporador está instalado e posicionado corretamente.",
        recommendedActions = listOf(
            "Corrigir o posicionamento do defletor.",
            "Reinstalar componentes montados incorretamente.",
            "Substituir peças danificadas ou ausentes."
        )
    ),

    DiagnosticCheck(
        title = "Limpeza e condições das aletas do trocador de calor",
        description = "Verifique se o fluxo de ar está livre. Aletas amassadas ou sujas dificultam a troca de calor e podem contribuir para condições inadequadas de funcionamento do evaporador, incluindo retorno de líquido e congelamento excessivo.",
        recommendedActions = listOf(
            "Realizar a limpeza adequada das aletas do evaporador.",
            "Corrigir cuidadosamente aletas amassadas, quando possível.",
            "Investigar as condições de superaquecimento e possível retorno de líquido por meio de medições do circuito frigorífico."
        )
    )
)

val airflowObstructionChecks = listOf(

    DiagnosticCheck(
        title = "Acúmulo de sujeira e bolor nas colmeias",
        description = "Verifique possíveis acúmulos de sujeira ou bolor nas partes interna e externa da colmeia.",
        recommendedActions = listOf(
            "Realizar a limpeza das partes interna e externa da colmeia.",
            "Remover completamente os resíduos e focos de bolor.",
            "Verificar as condições da colmeia após a limpeza."
        )
    ),

    DiagnosticCheck(
        title = "Obstruções nas grelhas de insuflamento",
        description = "Verifique possíveis obstruções nas grelhas de insuflamento, tanto na parte interna quanto na externa.",
        recommendedActions = listOf(
            "Remover materiais que estejam obstruindo as grelhas.",
            "Realizar a limpeza das partes interna e externa.",
            "Garantir que as grelhas estejam livres para permitir a circulação de ar."
        )
    ),

    DiagnosticCheck(
        title = "Deformações nas colmeias",
        description = "Verifique possíveis deformações nas colmeias que possam impedir ou restringir o fluxo de ar.",
        recommendedActions = listOf(
            "Corrigir deformações, quando tecnicamente possível.",
            "Reposicionar corretamente a colmeia.",
            "Substituir a colmeia caso apresente danos que comprometam o fluxo de ar."
        )
    )
)