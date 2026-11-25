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

val evaporatorAirflowBlockageChecks =
    evaporatorIceChecks + airflowObstructionChecks

val motorsLowRotationInvestigation = DiagnosticInvestigation(
    type = DiagnosticType.MOTORS_LOW_ROTATION,
    causeName = "Motores com rotação abaixo do esperado",
    initialQuestion = DiagnosticQuestion(
        question = "A redução da ventilação ocorre em todos os motores ou somente em alguns?",
        answers = listOf(
            "Todos os motores",
            "Somente alguns motores"
        )
    )
)

val lowRotationAnswers = listOf(
    DiagnosticAnswer(
        text = "Todos os motores",
        nextStep = "ALL_MOTORS_LOW_ROTATION_CHECK"
    ),
    DiagnosticAnswer(
        text = "Somente alguns motores",
        nextStep = "INDIVIDUAL_MOTORS_LOW_ROTATION_CHECK"
    )
)

val allMotorsLowRotationChecks = listOf(

    DiagnosticCheck(
        title = "Tensão de alimentação",
        description = "Verifique a tensão de alimentação dos motores durante o funcionamento e compare o valor encontrado com a especificação do fabricante.",
        recommendedActions = listOf(
            "Investigar possíveis quedas de tensão na alimentação.",
            "Verificar se a tensão fornecida está de acordo com as especificações dos motores.",
            "Corrigir irregularidades identificadas no circuito de alimentação."
        )
    ),

    DiagnosticCheck(
        title = "Conexões elétricas",
        description = "Verifique os terminais, bornes e conexões elétricas do circuito de alimentação dos motores, procurando oxidação, mau contato ou conexões frouxas.",
        recommendedActions = listOf(
            "Corrigir conexões com mau contato.",
            "Limpar ou substituir terminais oxidados ou danificados.",
            "Garantir o aperto adequado das conexões."
        )
    ),

    DiagnosticCheck(
        title = "Condutores e emendas",
        description = "Verifique a integridade dos condutores de alimentação e possíveis emendas inadequadas que possam provocar quedas de tensão.",
        recommendedActions = listOf(
            "Substituir condutores danificados.",
            "Refazer emendas inadequadas.",
            "Corrigir possíveis pontos de resistência elétrica elevada."
        )
    ),

    DiagnosticCheck(
        title = "Circuito de alimentação e acionamento",
        description = "Verifique o funcionamento dos contatores, relés e demais componentes responsáveis pela alimentação e acionamento dos motores.",
        recommendedActions = listOf(
            "Investigar possíveis falhas nos contatos de potência.",
            "Corrigir irregularidades no circuito de acionamento.",
            "Substituir componentes que apresentem defeito confirmado."
        )
    )
)

val individualLowRotationChecks = listOf(

    DiagnosticCheck(
        title = "Alimentação individual do micromotor",
        description = "Verifique a tensão de alimentação diretamente no micromotor durante o funcionamento e compare com a especificação do fabricante.",
        recommendedActions = listOf(
            "Investigar possíveis quedas de tensão no circuito individual.",
            "Corrigir irregularidades na alimentação.",
            "Verificar novamente a tensão após as correções."
        )
    ),

    DiagnosticCheck(
        title = "Conectores e terminais",
        description = "Verifique o conector e os terminais individuais do micromotor, procurando oxidação, mau contato ou danos.",
        recommendedActions = listOf(
            "Corrigir problemas de contato.",
            "Substituir terminais danificados.",
            "Substituir conectores comprometidos."
        )
    ),

    DiagnosticCheck(
        title = "Condutores individuais",
        description = "Verifique a integridade dos condutores responsáveis pela alimentação individual do micromotor.",
        recommendedActions = listOf(
            "Corrigir interrupções no circuito.",
            "Substituir trechos de condutores danificados.",
            "Refazer conexões inadequadas."
        )
    ),

    DiagnosticCheck(
        title = "Condições mecânicas",
        description = "Verifique se existem atritos, travamentos ou interferências mecânicas que possam dificultar a movimentação do conjunto de ventilação.",
        recommendedActions = listOf(
            "Eliminar possíveis interferências mecânicas.",
            "Corrigir problemas no suporte de sustentação.",
            "Garantir movimentação livre do conjunto."
        )
    ),

    DiagnosticCheck(
        title = "Condição do micromotor",
        description = "Se a alimentação elétrica estiver dentro das especificações e não existirem interferências mecânicas, considere a possibilidade de defeito interno do micromotor.",
        recommendedActions = listOf(
            "Confirmar a suspeita de defeito do micromotor.",
            "Substituir o micromotor por outro compatível com as especificações do equipamento.",
            "Verificar o funcionamento após a substituição."
        )
    )
)

val ventilationModuleChecks = listOf(

    DiagnosticCheck(
        title = "Posicionamento do módulo",
        description = "Verifique os encaixes e o correto posicionamento do módulo de ventilação.",
        recommendedActions = listOf(
            "Reposicionar o módulo corretamente.",
            "Verificar se todos os encaixes estão devidamente fixados.",
            "Garantir que o módulo esteja instalado conforme sua posição original."
        )
    ),

    DiagnosticCheck(
        title = "Encaixe da estrutura do módulo",
        description = "Verifique se a estrutura do módulo está completamente encaixada e corretamente posicionada para garantir máxima performance.",
        recommendedActions = listOf(
            "Corrigir o posicionamento da estrutura.",
            "Realizar o encaixe completo do módulo.",
            "Verificar se não existem folgas ou desalinhamentos."
        )
    ),

    DiagnosticCheck(
        title = "Fixação e segurança do módulo",
        description = "Verifique se o módulo está devidamente fixado e se existe risco de queda decorrente das vibrações durante o funcionamento.",
        recommendedActions = listOf(
            "Restabelecer a fixação adequada do módulo.",
            "Corrigir ou substituir elementos de fixação danificados.",
            "Garantir a estabilidade do conjunto antes de colocar o equipamento em operação."
        )
    )
)

val airDeflectorChecks = listOf(

    DiagnosticCheck(
        title = "Presença do defletor ou direcionador de ar",
        description = "Verifique se o defletor ou direcionador de ar está presente acima do evaporador.",
        recommendedActions = listOf(
            "Instalar o defletor ou direcionador de ar adequado ao equipamento.",
            "Verificar se o componente corresponde ao modelo do equipamento.",
            "Confirmar se a instalação atende à configuração original."
        )
    ),

    DiagnosticCheck(
        title = "Posicionamento e orientação do defletor",
        description = "Verifique se o defletor está corretamente posicionado e orientado para direcionar o fluxo de ar conforme o projeto do equipamento.",
        recommendedActions = listOf(
            "Corrigir o posicionamento do defletor.",
            "Ajustar sua orientação conforme a configuração original.",
            "Verificar se o fluxo de ar está sendo direcionado corretamente."
        )
    ),

    DiagnosticCheck(
        title = "Fixação e integridade do defletor",
        description = "Verifique as condições estruturais do defletor, seus pontos de fixação e possíveis deformações ou danos.",
        recommendedActions = listOf(
            "Restabelecer a fixação adequada do componente.",
            "Corrigir ou substituir elementos de fixação danificados.",
            "Substituir o defletor caso apresente danos ou deformações que comprometam sua função."
        )
    )
)

val propellerChecks = listOf(

    DiagnosticCheck(
        title = "Integridade das hélices",
        description = "Verifique a existência de trincas, rachaduras, quebras ou deformações nas hélices.",
        recommendedActions = listOf(
            "Substituir hélices que apresentem trincas, rachaduras ou quebras.",
            "Substituir hélices deformadas que comprometam a ventilação.",
            "Utilizar componentes compatíveis com o equipamento."
        )
    ),

    DiagnosticCheck(
        title = "Ângulo e orientação das pás",
        description = "Verifique se o ângulo e a orientação das pás estão corretos, conforme as especificações do fabricante.",
        recommendedActions = listOf(
            "Corrigir a orientação da hélice, quando aplicável.",
            "Substituir hélices com ângulo ou geometria incompatíveis.",
            "Respeitar as especificações originais do fabricante."
        )
    ),

    DiagnosticCheck(
        title = "Montagem e fixação das hélices",
        description = "Verifique o encaixe da hélice no eixo do micromotor, o sentido de montagem, o alinhamento e sua fixação.",
        recommendedActions = listOf(
            "Corrigir o encaixe e o sentido de montagem da hélice.",
            "Restabelecer a fixação adequada.",
            "Substituir componentes de fixação danificados."
        )
    )
)

// =====================================================
// TEMPERATURA DE INSUFLAMENTO ELEVADA
// =====================================================

// 1. Baixa capacidade de refrigeração do evaporador

val lowEvaporatorCapacityChecks = listOf(

    DiagnosticCheck(
        title = "Limpeza da serpentina do evaporador",
        description = "Verifique se há acúmulo de sujeira, gordura ou resíduos na serpentina, prejudicando a troca térmica.",
        recommendedActions = listOf(
            "Realizar a limpeza da serpentina do evaporador.",
            "Verificar possíveis obstruções entre as aletas.",
            "Confirmar se a troca térmica foi restabelecida após a limpeza."
        )
    ),

    DiagnosticCheck(
        title = "Integridade das aletas do evaporador",
        description = "Inspecione as aletas quanto a deformações, amassamentos ou danos que possam comprometer a passagem de ar e a troca térmica.",
        recommendedActions = listOf(
            "Corrigir deformações quando tecnicamente possível.",
            "Avaliar a necessidade de substituição do evaporador caso os danos comprometam sua eficiência."
        )
    ),

    DiagnosticCheck(
        title = "Eficiência da troca térmica",
        description = "Meça as temperaturas do ar de retorno e do ar de insuflamento para avaliar o diferencial térmico do evaporador.",
        recommendedActions = listOf(
            "Comparar o diferencial de temperatura com os valores esperados para o equipamento.",
            "Investigar possíveis causas caso o diferencial esteja abaixo do esperado.",
            "Registrar as medições para acompanhamento."
        )
    )
)


// 2. Vazão de refrigerante insuficiente no evaporador

val insufficientRefrigerantFlowChecks = listOf(

    DiagnosticCheck(
        title = "Superaquecimento na saída do evaporador",
        description = "Meça a pressão de sucção e a temperatura da linha de sucção na saída do evaporador para calcular o superaquecimento.",
        recommendedActions = listOf(
            "Comparar o superaquecimento com os parâmetros esperados para o sistema.",
            "Investigar valores excessivamente elevados, que podem indicar alimentação insuficiente de refrigerante.",
            "Considerar as condições de funcionamento antes de concluir o diagnóstico."
        )
    ),

    DiagnosticCheck(
        title = "Distribuição de refrigerante no evaporador",
        description = "Avalie se os circuitos do evaporador estão recebendo refrigerante de maneira adequada e uniforme.",
        recommendedActions = listOf(
            "Investigar diferenças anormais de temperatura entre os circuitos.",
            "Verificar possíveis obstruções no distribuidor de refrigerante e nos capilares de distribuição.",
            "Corrigir irregularidades identificadas na distribuição."
        )
    ),

    DiagnosticCheck(
        title = "Condições de pressão e temperatura do circuito frigorífico",
        description = "Analise as pressões e temperaturas de operação do circuito frigorífico, considerando as condições de funcionamento do evaporador.",
        recommendedActions = listOf(
            "Comparar os valores medidos com os parâmetros esperados.",
            "Investigar condições que possam indicar alimentação insuficiente do evaporador.",
            "Registrar as medições para auxiliar na análise."
        )
    )
)


// 3. Evaporador com excesso de gelo
// Reutilizar a lista existente: evaporatorIceChecks.


// 4. Válvula de expansão com funcionamento inadequado

val expansionValveChecks = listOf(

    DiagnosticCheck(
        title = "Superaquecimento na saída do evaporador",
        description = "Meça o superaquecimento na saída do evaporador para avaliar se a válvula está alimentando adequadamente o evaporador.",
        recommendedActions = listOf(
            "Comparar o valor encontrado com os parâmetros recomendados.",
            "Investigar superaquecimento excessivo.",
            "Verificar a regulagem da válvula quando aplicável."
        )
    ),

    DiagnosticCheck(
        title = "Bulbo sensor da válvula de expansão",
        description = "Verifique o posicionamento, a fixação e o isolamento térmico do bulbo sensor da válvula.",
        recommendedActions = listOf(
            "Corrigir o posicionamento conforme orientação do fabricante.",
            "Garantir bom contato térmico com a tubulação.",
            "Verificar se o isolamento térmico está adequado."
        )
    ),

    DiagnosticCheck(
        title = "Equalização de pressão",
        description = "Quando houver equalização externa, verifique a tubulação quanto a obstruções, vazamentos ou danos.",
        recommendedActions = listOf(
            "Verificar a integridade da linha de equalização.",
            "Corrigir obstruções ou vazamentos identificados.",
            "Confirmar o funcionamento adequado da equalização."
        )
    ),

    DiagnosticCheck(
        title = "Restrição ou obstrução na válvula",
        description = "Investigue a presença de partículas, resíduos ou umidade que possam restringir a passagem de refrigerante pela válvula.",
        recommendedActions = listOf(
            "Verificar possíveis obstruções no orifício da válvula.",
            "Realizar limpeza ou substituição quando necessário.",
            "Investigar a origem dos contaminantes presentes no circuito."
        )
    ),

    DiagnosticCheck(
        title = "Funcionamento do mecanismo da válvula",
        description = "Avalie a resposta da válvula às variações de carga térmica e às condições de operação do sistema.",
        recommendedActions = listOf(
            "Investigar travamentos ou perda de resposta do mecanismo.",
            "Verificar a integridade do elemento termostático.",
            "Substituir a válvula caso seja confirmada falha de funcionamento."
        )
    )
)


// 5. Temperatura de evaporação elevada

val highEvaporationTemperatureChecks = listOf(

    DiagnosticCheck(
        title = "Pressão de sucção",
        description = "Meça a pressão de sucção e compare com a pressão correspondente à temperatura de evaporação esperada para o refrigerante utilizado.",
        recommendedActions = listOf(
            "Consultar a tabela de pressão e temperatura do refrigerante.",
            "Comparar a temperatura de evaporação encontrada com a condição de projeto.",
            "Investigar pressões incompatíveis com a operação esperada."
        )
    ),

    DiagnosticCheck(
        title = "Regulagem do controle de pressão de evaporação",
        description = "Verifique os parâmetros e o funcionamento do controlador ou regulador de pressão de evaporação, quando existente.",
        recommendedActions = listOf(
            "Conferir os parâmetros configurados.",
            "Verificar a atuação do dispositivo de controle.",
            "Corrigir regulagens inadequadas conforme as especificações do sistema."
        )
    ),

    DiagnosticCheck(
        title = "Condições de funcionamento dos compressores",
        description = "Avalie se a capacidade frigorífica disponível dos compressores é compatível com a carga térmica do sistema.",
        recommendedActions = listOf(
            "Verificar o funcionamento dos compressores.",
            "Avaliar se a capacidade disponível atende à demanda frigorífica.",
            "Investigar possíveis limitações de capacidade."
        )
    ),

    DiagnosticCheck(
        title = "Condições de operação do sistema frigorífico",
        description = "Analise as condições gerais de pressão e temperatura do circuito para identificar situações que possam elevar a temperatura de evaporação.",
        recommendedActions = listOf(
            "Registrar as pressões e temperaturas de operação.",
            "Comparar os valores com as condições esperadas do sistema.",
            "Investigar as condições anormais identificadas."
        )
    ),

    DiagnosticCheck(
        title = "Atuação inadequada do controle de capacidade dos compressores",
        description = "Quando houver controle de capacidade, verifique a atuação dos estágios, da modulação ou dos mecanismos de descarregamento dos compressores.",
        recommendedActions = listOf(
            "Verificar os parâmetros do controle de capacidade.",
            "Avaliar a atuação dos estágios de capacidade.",
            "Investigar falhas de modulação ou descarregamento."
        )
    )
)


// 6. Ar de retorno com temperatura excessivamente elevada

val highReturnAirTemperatureChecks = listOf(

    DiagnosticCheck(
        title = "Temperatura do ambiente ou dos produtos",
        description = "Meça a temperatura do ambiente e dos produtos armazenados para avaliar se a carga térmica está dentro das condições previstas.",
        recommendedActions = listOf(
            "Verificar se a carga térmica está dentro dos limites do equipamento.",
            "Avaliar excesso de produtos ou armazenamento inadequado.",
            "Orientar a disposição dos produtos conforme as recomendações do equipamento."
        )
    ),

    DiagnosticCheck(
        title = "Infiltração de ar quente",
        description = "Inspecione portas, cortinas, tampas, vedações e demais pontos que possam permitir a entrada de ar quente.",
        recommendedActions = listOf(
            "Corrigir falhas de vedação.",
            "Substituir componentes danificados.",
            "Verificar se portas e tampas estão fechando corretamente."
        )
    ),

    DiagnosticCheck(
        title = "Condições de carregamento e disposição dos produtos",
        description = "Verifique se a quantidade e a disposição dos produtos permitem a circulação adequada do ar no equipamento.",
        recommendedActions = listOf(
            "Verificar se os limites de carregamento estão sendo respeitados.",
            "Desobstruir entradas e saídas de ar.",
            "Orientar a disposição correta dos produtos."
        )
    ),

    DiagnosticCheck(
        title = "Exposição excessiva a fontes de calor",
        description = "Verifique a existência de fontes externas de calor, exposição solar ou equipamentos próximos que possam elevar a carga térmica.",
        recommendedActions = listOf(
            "Identificar possíveis fontes de calor próximas.",
            "Reduzir a exposição quando possível.",
            "Avaliar as condições de instalação e ventilação do equipamento."
        )
    ),

    DiagnosticCheck(
        title = "Temperatura do ar de retorno",
        description = "Meça as temperaturas do ar de retorno e de insuflamento durante a operação do equipamento.",
        recommendedActions = listOf(
            "Registrar as temperaturas medidas.",
            "Avaliar o diferencial térmico encontrado.",
            "Investigar os motivos da temperatura elevada do ar de retorno."
        )
    )
)


// 7. Sensores de temperatura com leitura incorreta

val temperatureSensorChecks = listOf(

    DiagnosticCheck(
        title = "Posicionamento do sensor",
        description = "Verifique se o sensor está instalado na posição prevista pelo fabricante e pelo projeto do equipamento.",
        recommendedActions = listOf(
            "Corrigir o posicionamento quando necessário.",
            "Verificar se o sensor está exposto a fontes de calor que possam interferir na leitura."
        )
    ),

    DiagnosticCheck(
        title = "Fixação e contato térmico do sensor",
        description = "Inspecione a fixação e o contato térmico do sensor com a superfície ou ambiente que deve ser monitorado.",
        recommendedActions = listOf(
            "Corrigir a fixação do sensor.",
            "Garantir contato térmico adequado.",
            "Verificar o isolamento térmico quando aplicável."
        )
    ),

    DiagnosticCheck(
        title = "Integridade do sensor",
        description = "Verifique a existência de danos físicos, oxidação, umidade ou deterioração no sensor.",
        recommendedActions = listOf(
            "Inspecionar o estado físico do sensor.",
            "Corrigir problemas de conexão identificados.",
            "Substituir o sensor caso seja constatada falha."
        )
    ),

    DiagnosticCheck(
        title = "Condições da fiação e conexões",
        description = "Inspecione cabos, emendas, conectores e terminais relacionados ao circuito do sensor.",
        recommendedActions = listOf(
            "Corrigir conexões frouxas ou oxidadas.",
            "Verificar possíveis interrupções ou curtos-circuitos.",
            "Substituir trechos de fiação danificados."
        )
    ),

    DiagnosticCheck(
        title = "Precisão da leitura de temperatura",
        description = "Compare a temperatura indicada pelo controlador com a leitura de um instrumento de referência confiável.",
        recommendedActions = listOf(
            "Verificar possíveis desvios entre as leituras.",
            "Conferir a configuração do tipo de sensor no controlador.",
            "Substituir o sensor caso esteja fora da tolerância especificada."
        )
    )
)


// 8. Presença excessiva de óleo no evaporador

val excessOilInEvaporatorChecks = listOf(

    DiagnosticCheck(
        title = "Indícios de acúmulo excessivo de óleo no evaporador",
        description = "Avalie o comportamento térmico do evaporador e procure indícios de retenção excessiva de óleo em seus circuitos.",
        recommendedActions = listOf(
            "Investigar possíveis regiões com retenção de óleo.",
            "Avaliar o comportamento térmico dos circuitos.",
            "Não concluir o diagnóstico apenas pela aparência ou diferença de temperatura."
        )
    ),

    DiagnosticCheck(
        title = "Condições de retorno de óleo ao sistema",
        description = "Avalie as condições de retorno de óleo ao sistema, considerando o projeto da instalação e as condições de operação.",
        recommendedActions = listOf(
            "Verificar possíveis dificuldades de retorno de óleo.",
            "Avaliar as condições de velocidade e circulação do refrigerante.",
            "Investigar possíveis irregularidades no projeto ou na operação."
        )
    ),

    DiagnosticCheck(
        title = "Funcionamento do sistema de gerenciamento de óleo",
        description = "Quando existente, inspecione o separador de óleo, reservatório, reguladores de nível e demais componentes do gerenciamento de óleo.",
        recommendedActions = listOf(
            "Verificar o funcionamento do separador de óleo.",
            "Avaliar o controle de nível e a alimentação de óleo.",
            "Investigar falhas nos componentes de retorno e gerenciamento."
        )
    ),

    DiagnosticCheck(
        title = "Condições de superaquecimento e operação do evaporador",
        description = "Avalie o superaquecimento e as condições de operação que possam favorecer o transporte inadequado ou a retenção de óleo no evaporador.",
        recommendedActions = listOf(
            "Medir e avaliar o superaquecimento.",
            "Investigar condições operacionais que prejudiquem o retorno de óleo.",
            "Comparar os resultados com as condições previstas para o sistema."
        )
    ),

    DiagnosticCheck(
        title = "Especificação do óleo utilizado na última troca",
        description = "Verifique se o óleo utilizado na última manutenção atende às especificações recomendadas pelo fabricante do compressor e do sistema.",
        recommendedActions = listOf(
            "Conferir o tipo de óleo utilizado.",
            "Verificar viscosidade, classificação e especificações técnicas.",
            "Corrigir a utilização de óleo inadequado conforme orientação do fabricante."
        )
    )
)


// 9. Filtro secador saturado (se houver)

val filterDrierChecks = listOf(

    DiagnosticCheck(
        title = "Diferencial de temperatura na entrada e saída do filtro",
        description = "Meça e compare as temperaturas na entrada e na saída do filtro secador durante o funcionamento do sistema.",
        recommendedActions = listOf(
            "Registrar as temperaturas medidas.",
            "Investigar diferenças anormais que possam indicar restrição.",
            "Avaliar a necessidade de substituição do filtro."
        )
    ),

    DiagnosticCheck(
        title = "Diferencial de pressão no filtro secador",
        description = "Avalie a queda de pressão entre a entrada e a saída do filtro secador utilizando instrumentos adequados.",
        recommendedActions = listOf(
            "Medir a diferença de pressão durante a operação.",
            "Comparar o resultado com os valores esperados.",
            "Substituir o filtro caso a restrição ou saturação seja confirmada."
        )
    ),

    DiagnosticCheck(
        title = "Indicador de umidade e líquido (visor)",
        description = "Quando houver visor com indicador de umidade, verifique a coloração do elemento indicador e as condições do refrigerante.",
        recommendedActions = listOf(
            "Comparar a coloração com a escala indicada pelo fabricante.",
            "Avaliar a presença de umidade no sistema.",
            "Não considerar a presença de bolhas, isoladamente, como confirmação de saturação do filtro."
        )
    ),

    DiagnosticCheck(
        title = "Histórico e condição do filtro secador",
        description = "Verifique o histórico de manutenção, o tempo de utilização e possíveis condições de contaminação do filtro.",
        recommendedActions = listOf(
            "Consultar o histórico de substituição do filtro.",
            "Avaliar se o circuito foi aberto ou sofreu contaminação.",
            "Considerar a substituição e os procedimentos adequados de evacuação do sistema."
        )
    )
)


// 10. Baixo nível de fluido líquido no tanque

val lowLiquidReceiverLevelChecks = listOf(

    DiagnosticCheck(
        title = "Quantidade de refrigerante no sistema",
        description = "Avalie se a quantidade de refrigerante presente no sistema está de acordo com as especificações da instalação.",
        recommendedActions = listOf(
            "Investigar possível quantidade insuficiente de refrigerante.",
            "Consultar o histórico de manutenção e intervenções no sistema.",
            "Corrigir a carga conforme as especificações do fabricante e do projeto."
        )
    ),

    DiagnosticCheck(
        title = "Possíveis vazamentos de refrigerante",
        description = "Inspecione o circuito frigorífico para identificar possíveis vazamentos que possam reduzir a quantidade de refrigerante no sistema.",
        recommendedActions = listOf(
            "Realizar teste de vazamento conforme procedimento técnico adequado.",
            "Corrigir os vazamentos identificados.",
            "Após o reparo, executar os procedimentos adequados de vácuo e carga de refrigerante."
        )
    )
)

// =====================================================
// TEMPERATURA DE INSUFLAMENTO OSCILANDO
// =====================================================

// 1. Sensor de temperatura com leitura instável ou incorreta

val unstableTemperatureSensorChecks = listOf(

    DiagnosticCheck(
        title = "Integridade e conexão elétrica do sensor",
        description = "Inspecione o sensor de temperatura, seus cabos, conectores e pontos de conexão. Verifique mau contato, oxidação, emendas inadequadas ou danos que possam provocar variações na leitura.",
        recommendedActions = listOf(
            "Corrigir conexões elétricas com mau contato.",
            "Verificar a integridade dos cabos e conectores.",
            "Substituir o sensor caso apresente danos ou defeitos."
        )
    ),

    DiagnosticCheck(
        title = "Estabilidade da leitura de temperatura",
        description = "Acompanhe a leitura apresentada pelo controlador e observe se ocorrem oscilações bruscas ou variações incompatíveis com as condições reais de funcionamento.",
        recommendedActions = listOf(
            "Verificar se as oscilações também ocorrem na medição física da temperatura.",
            "Investigar possíveis falhas intermitentes no sensor ou em sua conexão.",
            "Avaliar a resposta do sensor durante o funcionamento do equipamento."
        )
    ),

    DiagnosticCheck(
        title = "Comparação com instrumento de referência",
        description = "Compare a temperatura indicada pelo controlador com a medição realizada por um instrumento de referência, utilizando um ponto de medição e condições equivalentes.",
        recommendedActions = listOf(
            "Verificar se existe diferença significativa entre as leituras.",
            "Avaliar a precisão do sensor e a compatibilidade com o controlador.",
            "Substituir ou corrigir o sensor caso seja confirmada uma leitura incorreta."
        )
    )
)

// 2. Controlador eletrônico com parâmetros de controle inadequados

val temperatureControllerChecks = listOf(

    DiagnosticCheck(
        title = "Configuração do diferencial (histerese)",
        description = "Verifique se o diferencial ou histerese de controle está configurado adequadamente para a aplicação. Valores inadequados podem provocar acionamentos frequentes e oscilações de temperatura.",
        recommendedActions = listOf(
            "Conferir o diferencial configurado no controlador.",
            "Comparar o parâmetro com as recomendações do fabricante.",
            "Ajustar o diferencial quando necessário, respeitando as características do equipamento."
        )
    ),

    DiagnosticCheck(
        title = "Parâmetros de controle de temperatura",
        description = "Verifique se o setpoint e os demais parâmetros de controle estão configurados de acordo com as condições de operação e as características do equipamento.",
        recommendedActions = listOf(
            "Conferir o setpoint configurado.",
            "Verificar parâmetros de controle que possam provocar oscilações.",
            "Restabelecer os parâmetros recomendados pelo fabricante, quando necessário."
        )
    ),

    DiagnosticCheck(
        title = "Histórico de acionamentos e variações de temperatura",
        description = "Analise o histórico de temperatura e os registros de acionamento do controlador para identificar possíveis relações entre os comandos de controle e as oscilações observadas.",
        recommendedActions = listOf(
            "Verificar a frequência dos acionamentos.",
            "Identificar padrões de oscilação associados aos comandos do controlador.",
            "Reavaliar os parâmetros de controle conforme os resultados encontrados."
        )
    )
)

// 3. Válvula de expansão com funcionamento instável (hunting)

val expansionValveHuntingChecks = listOf(

    DiagnosticCheck(
        title = "Oscilação do superaquecimento",
        description = "Monitore a pressão de sucção e a temperatura da linha de sucção na saída do evaporador. Observe se ocorrem variações cíclicas e frequentes no superaquecimento.",
        recommendedActions = listOf(
            "Registrar as variações de superaquecimento durante o funcionamento.",
            "Verificar se as oscilações apresentam comportamento cíclico.",
            "Investigar a estabilidade da alimentação de refrigerante."
        )
    ),

    DiagnosticCheck(
        title = "Estabilidade da alimentação do evaporador",
        description = "Observe o comportamento da alimentação de refrigerante no evaporador, verificando possíveis alternâncias entre condições de excesso e insuficiência de alimentação.",
        recommendedActions = listOf(
            "Verificar possíveis oscilações na alimentação de refrigerante.",
            "Avaliar a distribuição de refrigerante no evaporador.",
            "Investigar condições que possam provocar instabilidade na válvula."
        )
    ),

    DiagnosticCheck(
        title = "Dimensionamento e capacidade da válvula",
        description = "Verifique se a capacidade da válvula de expansão é compatível com a capacidade frigorífica e as condições de operação do evaporador.",
        recommendedActions = listOf(
            "Conferir o modelo e a capacidade nominal da válvula.",
            "Comparar sua capacidade com as condições reais de operação.",
            "Avaliar a necessidade de correção do dimensionamento."
        )
    ),

    DiagnosticCheck(
        title = "Bulbo sensor e equalização da válvula",
        description = "Nas válvulas termostáticas, inspecione a fixação e o posicionamento do bulbo sensor, o isolamento térmico e a linha de equalização externa, quando existente.",
        recommendedActions = listOf(
            "Corrigir o posicionamento e a fixação do bulbo.",
            "Verificar e restabelecer o isolamento térmico.",
            "Inspecionar a linha de equalização quanto a obstruções, vazamentos ou danos."
        )
    )
)

// 5. Variação frequente da capacidade frigorífica do Rack

val rackCapacityVariationChecks = listOf(

    DiagnosticCheck(
        title = "Frequência de acionamento dos compressores",
        description = "Observe se os compressores estão entrando e saindo de operação frequentemente, provocando variações na capacidade frigorífica disponível.",
        recommendedActions = listOf(
            "Verificar a frequência de partidas e paradas dos compressores.",
            "Identificar possíveis ciclos curtos de funcionamento.",
            "Investigar as condições que provocam alterações frequentes de capacidade."
        )
    ),

    DiagnosticCheck(
        title = "Estabilidade da pressão de sucção durante o escalonamento",
        description = "Monitore a pressão de sucção durante a entrada e saída dos compressores, verificando se ocorrem oscilações significativas após as mudanças de capacidade.",
        recommendedActions = listOf(
            "Registrar as variações de pressão durante o escalonamento.",
            "Verificar se as oscilações coincidem com os acionamentos dos compressores.",
            "Avaliar a estabilidade do controle de capacidade do Rack."
        )
    ),

    DiagnosticCheck(
        title = "Funcionamento do sistema de modulação de capacidade",
        description = "Verifique o funcionamento dos recursos de modulação de capacidade, como inversores de frequência, descarregadores de capacidade ou outros sistemas utilizados pelos compressores.",
        recommendedActions = listOf(
            "Verificar se a modulação ocorre de maneira estável.",
            "Investigar oscilações ou respostas inadequadas dos dispositivos de controle.",
            "Conferir os parâmetros de operação conforme as especificações do fabricante."
        )
    ),

    DiagnosticCheck(
        title = "Parâmetros de controle da pressão de sucção",
        description = "Verifique o setpoint, o diferencial de controle e os tempos de acionamento dos compressores, avaliando se os parâmetros favorecem alterações frequentes de capacidade.",
        recommendedActions = listOf(
            "Conferir o setpoint e o diferencial de pressão.",
            "Verificar os tempos mínimos de funcionamento e parada.",
            "Avaliar a necessidade de ajustes nos parâmetros de controle."
        )
    )
)

// 6. Controle de capacidade dos compressores provocando oscilações na sucção

val compressorCapacityControlChecks = listOf(

    DiagnosticCheck(
        title = "Resposta do controlador de sucção",
        description = "Verifique se os comandos de capacidade enviados pelo controlador correspondem às variações reais da pressão de sucção e se a resposta do sistema ocorre de maneira estável.",
        recommendedActions = listOf(
            "Comparar os valores de pressão medidos com os valores reconhecidos pelo controlador.",
            "Verificar possíveis atrasos ou instabilidades na resposta aos comandos.",
            "Conferir os parâmetros de controle conforme as especificações do fabricante."
        )
    ),

    DiagnosticCheck(
        title = "Sequenciamento dos compressores",
        description = "Verifique se a sequência de entrada e saída dos compressores ocorre adequadamente, evitando alterações bruscas ou frequentes na capacidade frigorífica.",
        recommendedActions = listOf(
            "Analisar a sequência de acionamento dos compressores.",
            "Verificar alternâncias excessivas entre compressores.",
            "Conferir os parâmetros de escalonamento e prioridades de funcionamento."
        )
    ),

    DiagnosticCheck(
        title = "Funcionamento dos inversores de frequência",
        description = "Nos compressores com inversores, observe se a frequência e a rotação variam de maneira estável, sem oscilações excessivas ou comandos inconsistentes.",
        recommendedActions = listOf(
            "Verificar a frequência de operação e os comandos de referência.",
            "Investigar oscilações de rotação ou falhas de comunicação.",
            "Conferir os parâmetros de modulação do inversor."
        )
    ),

    DiagnosticCheck(
        title = "Funcionamento dos descarregadores de capacidade",
        description = "Nos compressores que utilizam descarregadores de capacidade, verifique se os dispositivos atuam corretamente e se não apresentam falhas elétricas, mecânicas ou de acionamento.",
        recommendedActions = listOf(
            "Verificar a atuação dos descarregadores durante as mudanças de capacidade.",
            "Inspecionar componentes elétricos e eletromecânicos de acionamento.",
            "Corrigir falhas identificadas no sistema de descarga."
        )
    ),

    DiagnosticCheck(
        title = "Parâmetros de controle e temporização",
        description = "Avalie os diferenciais de pressão, tempos mínimos de funcionamento e parada, intervalos entre acionamentos e limites de modulação configurados no controlador.",
        recommendedActions = listOf(
            "Conferir os diferenciais de controle de pressão.",
            "Verificar os tempos mínimos e máximos de acionamento.",
            "Avaliar ajustes que possam melhorar a estabilidade da pressão de sucção."
        )
    )
)

// Verificações para ventiladores do evaporador com funcionamento intermitente
val intermittentEvaporatorFanChecks = listOf(

    DiagnosticCheck(
        title = "Continuidade do funcionamento dos motores",
        description = "Verifique se os motores dos ventiladores apresentam paradas inesperadas durante a operação.",
        recommendedActions = listOf(
            "Observe se os ventiladores interrompem o funcionamento sem que haja um comando de desligamento.",
            "Verifique se algum motor apresenta falhas intermitentes durante a operação."
        )
    ),

    DiagnosticCheck(
        title = "Alimentação elétrica dos ventiladores",
        description = "Verifique se existem falhas ou oscilações na alimentação elétrica dos motores.",
        recommendedActions = listOf(
            "Verifique a tensão de alimentação durante o funcionamento dos ventiladores.",
            "Inspecione conexões, relés, contatores e possíveis pontos de mau contato."
        )
    ),

    DiagnosticCheck(
        title = "Atuação do controle de ventilação",
        description = "Verifique se o controlador está provocando partidas e paradas frequentes dos ventiladores.",
        recommendedActions = listOf(
            "Observe os comandos de acionamento e desligamento enviados pelo controlador.",
            "Verifique os parâmetros de controle e as condições que determinam o acionamento dos ventiladores."
        )
    ),

    DiagnosticCheck(
        title = "Condições mecânicas dos ventiladores",
        description = "Verifique se existem problemas mecânicos que dificultam ou interrompem a rotação dos ventiladores.",
        recommendedActions = listOf(
            "Inspecione rolamentos, ruídos anormais, aquecimento e possíveis travamentos.",
            "Verifique se existe resistência mecânica excessiva durante a rotação."
        )
    )
)

// Verificações para ciclos de degelo interferindo na temperatura de insuflamento
val defrostTemperatureVariationChecks = listOf(

    DiagnosticCheck(
        title = "Frequência dos ciclos de degelo",
        description = "Verifique se os ciclos de degelo estão ocorrendo em intervalos excessivamente curtos.",
        recommendedActions = listOf(
            "Compare os intervalos entre os ciclos de degelo com a programação configurada.",
            "Verifique se o degelo está sendo iniciado frequentemente por condições inadequadas ou parâmetros incorretos."
        )
    ),

    DiagnosticCheck(
        title = "Duração dos ciclos de degelo",
        description = "Verifique se o tempo de degelo está adequado ou se existem prolongamentos excessivos.",
        recommendedActions = listOf(
            "Compare a duração real dos ciclos com o tempo configurado no controlador.",
            "Verifique se o degelo está sendo concluído dentro do tempo esperado."
        )
    ),

    DiagnosticCheck(
        title = "Término do degelo",
        description = "Verifique o funcionamento do sensor de fim de degelo e os parâmetros utilizados para encerrar o ciclo.",
        recommendedActions = listOf(
            "Confira a leitura e o posicionamento do sensor de término do degelo.",
            "Verifique os parâmetros de temperatura de encerramento e o tempo máximo de degelo."
        )
    ),

    DiagnosticCheck(
        title = "Retorno à refrigeração",
        description = "Verifique o comportamento da temperatura após o término do degelo e durante a retomada da refrigeração.",
        recommendedActions = listOf(
            "Verifique o tempo de gotejamento após o término do degelo.",
            "Confira o atraso na partida dos ventiladores e observe a recuperação da temperatura de insuflamento."
        )
    )
)

// Verificações para variação excessiva da carga térmica
val thermalLoadVariationChecks = listOf(

    DiagnosticCheck(
        title = "Variação da entrada de produtos",
        description = "Verifique se a entrada frequente de produtos em temperaturas elevadas ou em grandes quantidades provoca alterações na carga térmica.",
        recommendedActions = listOf(
            "Observe a frequência e a quantidade de produtos introduzidos no equipamento.",
            "Verifique se os produtos são armazenados em temperaturas acima da faixa de conservação."
        )
    ),

    DiagnosticCheck(
        title = "Frequência de abertura de portas ou tampas",
        description = "Verifique se a utilização frequente do equipamento provoca variações significativas na entrada de calor.",
        recommendedActions = listOf(
            "Observe a frequência e o tempo de abertura das portas ou tampas.",
            "Verifique se a rotina de utilização do equipamento favorece a entrada excessiva de ar quente."
        )
    ),

    DiagnosticCheck(
        title = "Distribuição dos produtos",
        description = "Verifique se a disposição ou o volume de produtos interfere na circulação de ar e na troca térmica.",
        recommendedActions = listOf(
            "Inspecione se os produtos estão obstruindo as passagens de ar.",
            "Verifique se o volume armazenado está dentro da capacidade recomendada para o equipamento."
        )
    ),

    DiagnosticCheck(
        title = "Condições de operação do equipamento",
        description = "Verifique se a carga térmica apresenta variações significativas em determinados horários ou períodos de funcionamento.",
        recommendedActions = listOf(
            "Compare as oscilações de temperatura com os horários de maior utilização do equipamento.",
            "Observe se existe relação entre o aumento da carga térmica e as variações na temperatura de insuflamento."
        )
    )
)

// Verificações para infiltração intermitente de ar externo
val externalAirInfiltrationChecks = listOf(

    DiagnosticCheck(
        title = "Vedação de portas, tampas e cortinas de ar",
        description = "Verifique se existem falhas de vedação que permitam a entrada de ar externo no equipamento.",
        recommendedActions = listOf(
            "Inspecione as borrachas de vedação quanto a danos, deformações e desgaste.",
            "Verifique folgas, desalinhamentos e falhas no fechamento de portas e tampas."
        )
    ),

    DiagnosticCheck(
        title = "Condições das cortinas de ar",
        description = "Verifique se as cortinas de ar estão funcionando corretamente e reduzindo a entrada de ar externo.",
        recommendedActions = listOf(
            "Confira o funcionamento dos ventiladores e a continuidade do fluxo de ar.",
            "Verifique se a vazão e a distribuição do ar são suficientes para formar uma barreira adequada."
        )
    ),

    DiagnosticCheck(
        title = "Aberturas e frestas no equipamento",
        description = "Inspecione regiões que possam permitir a entrada de ar externo por frestas ou falhas de vedação.",
        recommendedActions = listOf(
            "Verifique passagens de tubulações, junções, painéis e pontos de conexão.",
            "Identifique possíveis aberturas ou falhas de vedação na estrutura do equipamento."
        )
    ),

    DiagnosticCheck(
        title = "Relação entre infiltração e oscilação de temperatura",
        description = "Observe se as variações de temperatura de insuflamento coincidem com situações que favorecem a entrada de ar externo.",
        recommendedActions = listOf(
            "Compare os registros de temperatura com os períodos de abertura de portas e movimentação de pessoas.",
            "Observe se alterações nas condições do ambiente externo coincidem com as oscilações de temperatura."
        )
    )
)

// Verificações para oscilação da pressão de evaporação
val evaporatingPressureOscillationChecks = listOf(

    DiagnosticCheck(
        title = "Monitoramento da pressão de sucção",
        description = "Verifique se a pressão de sucção apresenta oscilações durante o funcionamento do sistema.",
        recommendedActions = listOf(
            "Monitore a pressão de sucção utilizando manômetros ou instrumentos adequados.",
            "Compare as leituras com a faixa de pressão esperada para as condições de operação."
        )
    ),

    DiagnosticCheck(
        title = "Estabilidade do superaquecimento",
        description = "Verifique se existem variações excessivas no superaquecimento associadas às oscilações da pressão de evaporação.",
        recommendedActions = listOf(
            "Monitore o superaquecimento na saída do evaporador.",
            "Observe se as variações do superaquecimento coincidem com as oscilações da pressão de sucção."
        )
    ),

    DiagnosticCheck(
        title = "Comportamento do controle de sucção do rack",
        description = "Verifique se o controlador mantém a pressão de sucção dentro da faixa configurada ou provoca oscilações frequentes.",
        recommendedActions = listOf(
            "Confira os parâmetros de controle e o setpoint de pressão de sucção.",
            "Analise o comportamento do controlador durante as variações de carga do sistema."
        )
    ),

    DiagnosticCheck(
        title = "Variação da pressão durante a operação",
        description = "Identifique se as oscilações de pressão ocorrem em situações específicas de funcionamento do sistema.",
        recommendedActions = listOf(
            "Observe o comportamento da pressão durante a entrada e saída de compressores.",
            "Compare as oscilações com alterações de carga térmica e mudanças nas condições de operação."
        )
    )
)

// =====================================================
// DISJUNTOR DESARMANDO INTERMITENTEMENTE
// =====================================================

// 1. Sobrecarga elétrica no circuito

val circuitOverloadChecks = listOf(

    DiagnosticCheck(
        title = "Medição da corrente elétrica do circuito",
        description = "Meça a corrente elétrica consumida pelo circuito utilizando alicate amperímetro e compare os valores encontrados com a corrente nominal prevista para a instalação.",
        recommendedActions = listOf(
            "Medir a corrente nas fases do circuito.",
            "Comparar os valores com a capacidade nominal do circuito.",
            "Identificar se existe corrente acima do limite admissível.",
            "Verificar se a sobrecarga ocorre continuamente ou em determinados momentos."
        )
    ),

    DiagnosticCheck(
        title = "Identificação dos equipamentos conectados ao circuito",
        description = "Verifique quais equipamentos estão alimentados pelo mesmo circuito e se a soma das correntes consumidas ultrapassa sua capacidade de operação.",
        recommendedActions = listOf(
            "Identificar todos os equipamentos alimentados pelo circuito.",
            "Consultar as correntes nominais dos equipamentos.",
            "Verificar possíveis ligações adicionais não previstas no projeto.",
            "Avaliar a necessidade de redistribuição das cargas."
        )
    ),

    DiagnosticCheck(
        title = "Sobrecarga durante partidas simultâneas",
        description = "Investigue se o disparo ocorre quando dois ou mais equipamentos entram em funcionamento simultaneamente, provocando um aumento momentâneo da corrente elétrica.",
        recommendedActions = listOf(
            "Monitorar a corrente durante as partidas.",
            "Verificar a ocorrência de partidas simultâneas.",
            "Avaliar a necessidade de escalonamento das partidas.",
            "Verificar se a proteção está adequada às características das cargas."
        )
    ),

    DiagnosticCheck(
        title = "Desequilíbrio de corrente entre fases",
        description = "Verifique se existe diferença significativa entre as correntes medidas nas fases, indicando distribuição inadequada das cargas ou possíveis anormalidades elétricas.",
        recommendedActions = listOf(
            "Medir individualmente as correntes das fases.",
            "Comparar os valores encontrados.",
            "Identificar possíveis concentrações de carga em uma única fase.",
            "Avaliar a redistribuição das cargas, quando aplicável."
        )
    ),

    DiagnosticCheck(
        title = "Monitoramento da corrente durante a operação",
        description = "Acompanhe o comportamento da corrente elétrica durante diferentes condições de funcionamento para identificar picos ou aumentos progressivos que possam provocar o disparo do disjuntor.",
        recommendedActions = listOf(
            "Monitorar a corrente durante ciclos de funcionamento.",
            "Observar o comportamento durante partidas e paradas.",
            "Identificar horários ou condições associados aos disparos.",
            "Registrar os valores encontrados para análise."
        )
    )
)

// 2. Motor de ventilação com falha elétrica intermitente

val intermittentMotorElectricalChecks = listOf(

    DiagnosticCheck(
        title = "Medição da corrente elétrica do motor",
        description = "Meça a corrente consumida pelo motor durante o funcionamento e verifique possíveis oscilações, picos ou valores acima do especificado pelo fabricante.",
        recommendedActions = listOf(
            "Medir a corrente de funcionamento.",
            "Comparar os valores com a corrente nominal.",
            "Observar oscilações durante a operação.",
            "Verificar se o consumo aumenta antes do disparo do disjuntor."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção dos enrolamentos do motor",
        description = "Verifique a integridade elétrica dos enrolamentos, identificando possíveis alterações de resistência, interrupções ou curtos entre espiras.",
        recommendedActions = listOf(
            "Medir a resistência dos enrolamentos.",
            "Comparar os resultados com os valores de referência do fabricante.",
            "Verificar possíveis interrupções ou irregularidades.",
            "Avaliar a necessidade de substituição do motor."
        )
    ),

    DiagnosticCheck(
        title = "Teste de isolamento elétrico",
        description = "Verifique as condições de isolamento entre os enrolamentos e a carcaça do motor, identificando possíveis fugas de corrente para aterramento.",
        recommendedActions = listOf(
            "Realizar teste com megômetro, conforme especificações do fabricante.",
            "Verificar a resistência de isolamento.",
            "Identificar possíveis sinais de deterioração.",
            "Substituir o motor caso o isolamento esteja comprometido."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção das conexões e terminais elétricos",
        description = "Verifique se existem conexões frouxas, oxidadas ou com sinais de aquecimento que possam provocar interrupções ou instabilidade elétrica durante o funcionamento.",
        recommendedActions = listOf(
            "Inspecionar terminais e conectores.",
            "Verificar sinais de carbonização ou oxidação.",
            "Avaliar as condições dos cabos de alimentação.",
            "Corrigir conexões inadequadas e substituir componentes danificados."
        )
    ),

    DiagnosticCheck(
        title = "Verificação do funcionamento após aquecimento",
        description = "Investigue se o motor apresenta alterações elétricas após atingir determinada temperatura de operação, indicando possível falha intermitente associada ao aquecimento dos enrolamentos ou dos componentes internos.",
        recommendedActions = listOf(
            "Monitorar o funcionamento durante um ciclo operacional.",
            "Acompanhar a corrente elétrica durante o aquecimento.",
            "Verificar se ocorrem interrupções ou alterações no funcionamento.",
            "Avaliar a substituição do motor caso a falha seja confirmada."
        )
    )
)

// Verificações para curto-circuito intermitente na instalação elétrica
val intermittentShortCircuitChecks = listOf(

    DiagnosticCheck(
        title = "Inspeção visual da instalação elétrica",
        description = "Verificar cabos, condutores e componentes em busca de danos, isolamento deteriorado, marcas de queimadura ou pontos de contato indevido entre condutores.",
        recommendedActions = listOf(
            "Inspecionar toda a instalação elétrica do equipamento.",
            "Verificar cabos prensados, danificados ou com isolamento comprometido.",
            "Identificar possíveis pontos de contato entre condutores.",
            "Substituir os componentes ou trechos de cabos danificados."
        )
    ),

    DiagnosticCheck(
        title = "Teste de continuidade entre condutores",
        description = "Verificar se existe continuidade elétrica indevida entre condutores que deveriam permanecer isolados entre si.",
        recommendedActions = listOf(
            "Desenergizar o circuito antes de iniciar os testes.",
            "Isolar os condutores e componentes necessários para a medição.",
            "Utilizar um multímetro para verificar a continuidade entre os condutores.",
            "Investigar qualquer continuidade não prevista no circuito."
        )
    ),

    DiagnosticCheck(
        title = "Teste de resistência de isolamento",
        description = "Avaliar as condições do isolamento elétrico dos cabos e componentes para identificar possíveis falhas que possam provocar curtos-circuitos.",
        recommendedActions = listOf(
            "Desenergizar o circuito antes do ensaio.",
            "Desconectar componentes eletrônicos sensíveis conforme orientação do fabricante.",
            "Realizar o teste com instrumento adequado.",
            "Comparar os resultados com os valores especificados pelo fabricante ou normas aplicáveis."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção de componentes e dispositivos elétricos",
        description = "Verificar contatores, relés, terminais, bornes e demais dispositivos em busca de danos, deformações ou falhas que possam provocar contato elétrico indevido.",
        recommendedActions = listOf(
            "Inspecionar os componentes elétricos do circuito.",
            "Verificar sinais de aquecimento, carbonização ou deformação.",
            "Avaliar as condições dos contatos e terminais.",
            "Substituir componentes que apresentem falhas."
        )
    ),

    DiagnosticCheck(
        title = "Investigação de falhas intermitentes durante a operação",
        description = "Identificar condições específicas de funcionamento que possam provocar o curto-circuito, como vibração, movimentação de cabos ou aquecimento dos componentes.",
        recommendedActions = listOf(
            "Verificar se o desarme ocorre em alguma etapa específica da operação.",
            "Investigar possíveis interferências causadas por vibração ou movimentação dos cabos.",
            "Inspecionar componentes que apresentem falhas após aquecimento.",
            "Registrar as condições em que a falha acontece para direcionar o diagnóstico."
        )
    )
)

// Verificações para fuga de corrente para aterramento
val groundLeakageChecks = listOf(

    DiagnosticCheck(
        title = "Teste de isolamento dos motores elétricos",
        description = "Avaliar as condições do isolamento elétrico dos motores para identificar possíveis fugas de corrente para a carcaça ou aterramento.",
        recommendedActions = listOf(
            "Desenergizar o circuito antes de iniciar os testes.",
            "Desconectar os motores do circuito de alimentação.",
            "Realizar o teste de isolamento com instrumento adequado.",
            "Comparar os resultados com os valores recomendados pelo fabricante."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção das resistências elétricas de degelo",
        description = "Verificar se as resistências de degelo apresentam danos ou deterioração do isolamento que possam provocar fuga de corrente.",
        recommendedActions = listOf(
            "Inspecionar visualmente as resistências e seus terminais.",
            "Verificar sinais de umidade, oxidação ou deterioração.",
            "Realizar teste de isolamento com as resistências desconectadas.",
            "Substituir resistências que apresentem isolamento comprometido."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção dos cabos e condutores elétricos",
        description = "Identificar danos no isolamento dos cabos que possam permitir contato indevido entre condutores energizados e partes metálicas aterradas.",
        recommendedActions = listOf(
            "Inspecionar os cabos ao longo de toda a instalação.",
            "Verificar pontos de atrito, esmagamento ou ressecamento.",
            "Identificar possíveis contatos entre condutores e estruturas metálicas.",
            "Substituir cabos que apresentem isolamento danificado."
        )
    ),

    DiagnosticCheck(
        title = "Verificação da corrente de fuga",
        description = "Medir a corrente de fuga do circuito para identificar possíveis perdas de corrente para o aterramento durante a operação.",
        recommendedActions = listOf(
            "Utilizar alicate amperímetro apropriado para medição de corrente de fuga.",
            "Avaliar a corrente residual nos condutores do circuito.",
            "Verificar se os valores estão dentro dos limites aplicáveis.",
            "Investigar os equipamentos e componentes associados à fuga identificada."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção das conexões de aterramento",
        description = "Verificar a continuidade e as condições das conexões do condutor de proteção, garantindo que as partes metálicas estejam corretamente aterradas.",
        recommendedActions = listOf(
            "Inspecionar as conexões do condutor de proteção.",
            "Verificar sinais de oxidação, mau contato ou desconexão.",
            "Avaliar a continuidade do aterramento com instrumento adequado.",
            "Corrigir conexões inadequadas ou interrompidas."
        )
    )
)

// Verificações para conexões elétricas frouxas ou oxidadas
val looseOrCorrodedConnectionsChecks = listOf(

    DiagnosticCheck(
        title = "Inspeção visual dos terminais e conexões",
        description = "Verificar terminais, bornes e pontos de conexão em busca de oxidação, corrosão, sujeira ou sinais de mau contato.",
        recommendedActions = listOf(
            "Inspecionar visualmente todas as conexões elétricas do circuito.",
            "Identificar sinais de oxidação, corrosão ou carbonização.",
            "Verificar se existem terminais danificados ou deformados.",
            "Realizar a limpeza ou substituição dos componentes comprometidos."
        )
    ),

    DiagnosticCheck(
        title = "Verificação do aperto das conexões",
        description = "Avaliar se os terminais e bornes estão devidamente fixados, evitando mau contato e aquecimento durante a operação.",
        recommendedActions = listOf(
            "Desenergizar o circuito antes de realizar qualquer intervenção.",
            "Verificar o aperto dos terminais conforme especificação do fabricante.",
            "Utilizar ferramenta adequada para o torque recomendado.",
            "Corrigir conexões frouxas e substituir componentes danificados."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção dos cabos e terminais",
        description = "Verificar as condições físicas dos cabos e terminais, identificando fios rompidos, crimpagens inadequadas ou sinais de deterioração.",
        recommendedActions = listOf(
            "Inspecionar as extremidades dos cabos e suas conexões.",
            "Verificar se existem fios rompidos ou desencapados.",
            "Avaliar a qualidade da crimpagem dos terminais.",
            "Refazer terminações inadequadas e substituir cabos danificados."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção de aquecimento nos pontos de conexão",
        description = "Identificar possíveis pontos de aquecimento anormal provocados por resistência de contato elevada em conexões elétricas.",
        recommendedActions = listOf(
            "Realizar inspeção termográfica durante condições normais de operação, quando aplicável.",
            "Identificar diferenças de temperatura entre conexões equivalentes.",
            "Investigar pontos com aquecimento anormal.",
            "Corrigir conexões defeituosas e verificar novamente após o reparo."
        )
    ),

    DiagnosticCheck(
        title = "Medição de queda de tensão nas conexões",
        description = "Avaliar a queda de tensão nos pontos de conexão para identificar possíveis resistências de contato elevadas.",
        recommendedActions = listOf(
            "Utilizar instrumento adequado para realizar a medição.",
            "Verificar a queda de tensão nos pontos de conexão durante a operação.",
            "Comparar os resultados entre conexões equivalentes.",
            "Corrigir pontos que apresentem queda de tensão anormal."
        )
    )
)

// Verificações para disjuntor com defeito ou desgaste interno
val faultyCircuitBreakerChecks = listOf(

    DiagnosticCheck(
        title = "Inspeção visual do disjuntor",
        description = "Verificar se o disjuntor apresenta sinais de aquecimento, deformação, carbonização ou danos externos.",
        recommendedActions = listOf(
            "Inspecionar a carcaça e os terminais do disjuntor.",
            "Verificar sinais de escurecimento ou derretimento.",
            "Avaliar possíveis danos mecânicos.",
            "Substituir o disjuntor caso sejam identificados danos."
        )
    ),

    DiagnosticCheck(
        title = "Verificação das características nominais",
        description = "Conferir se a corrente nominal, tensão e demais características do disjuntor correspondem às especificações do circuito.",
        recommendedActions = listOf(
            "Identificar os dados nominais indicados no disjuntor.",
            "Comparar as características com o projeto elétrico.",
            "Verificar a compatibilidade com a carga e os condutores.",
            "Corrigir eventuais incompatibilidades conforme o projeto e as normas aplicáveis."
        )
    ),

    DiagnosticCheck(
        title = "Investigação de disparos sem sobrecorrente identificada",
        description = "Avaliar se o disjuntor está desarmando mesmo quando não são identificadas condições de sobrecarga ou curto-circuito.",
        recommendedActions = listOf(
            "Registrar as condições em que ocorrem os disparos.",
            "Monitorar a corrente elétrica do circuito.",
            "Verificar possíveis influências da temperatura ambiente.",
            "Investigar a possibilidade de falha interna do disjuntor."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção dos contatos internos",
        description = "Avaliar possíveis sinais de desgaste, deterioração ou danos nos contatos internos que possam comprometer o funcionamento do disjuntor.",
        recommendedActions = listOf(
            "Verificar as condições dos contatos conforme procedimento do fabricante.",
            "Identificar sinais de desgaste ou carbonização.",
            "Avaliar possíveis danos provocados por aquecimento excessivo.",
            "Substituir o disjuntor quando houver comprometimento interno."
        )
    ),

    DiagnosticCheck(
        title = "Teste de funcionamento do mecanismo de disparo",
        description = "Verificar se o mecanismo de proteção apresenta funcionamento irregular ou disparos incompatíveis com suas características de atuação.",
        recommendedActions = listOf(
            "Consultar as especificações técnicas do fabricante.",
            "Realizar testes com equipamento apropriado, quando necessário.",
            "Verificar a atuação do mecanismo de disparo.",
            "Substituir o disjuntor caso seja constatada falha de funcionamento."
        )
    )
)

// Verificações para oscilação ou desequilíbrio da tensão de alimentação
val supplyVoltageFluctuationChecks = listOf(

    DiagnosticCheck(
        title = "Medição da tensão de alimentação",
        description = "Verificar se a tensão fornecida ao equipamento está de acordo com os valores nominais especificados pelo fabricante.",
        recommendedActions = listOf(
            "Medir a tensão entre fases e entre fase e neutro, quando aplicável.",
            "Comparar os valores encontrados com a tensão nominal do equipamento.",
            "Identificar possíveis subtensões ou sobretensões.",
            "Registrar os valores medidos para análise."
        )
    ),

    DiagnosticCheck(
        title = "Monitoramento da tensão durante a operação",
        description = "Avaliar se ocorrem variações de tensão durante o funcionamento dos equipamentos, especialmente nos momentos de partida de motores e compressores.",
        recommendedActions = listOf(
            "Monitorar a tensão durante diferentes condições de operação.",
            "Verificar possíveis quedas de tensão durante as partidas.",
            "Identificar variações que coincidam com o desarme da proteção.",
            "Registrar os momentos em que ocorrem as oscilações."
        )
    ),

    DiagnosticCheck(
        title = "Análise do desequilíbrio de tensão entre fases",
        description = "Verificar se existe diferença significativa entre as tensões das fases em sistemas trifásicos, condição que pode afetar o funcionamento dos motores.",
        recommendedActions = listOf(
            "Medir as tensões entre todas as fases.",
            "Comparar os valores encontrados.",
            "Calcular o percentual de desequilíbrio de tensão.",
            "Investigar a origem das diferenças identificadas."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção das conexões da alimentação elétrica",
        description = "Verificar se existem conexões inadequadas na alimentação elétrica que possam provocar quedas ou oscilações de tensão.",
        recommendedActions = listOf(
            "Inspecionar terminais, bornes e conexões da alimentação.",
            "Verificar sinais de aquecimento ou oxidação.",
            "Avaliar possíveis quedas de tensão nas conexões.",
            "Corrigir conexões defeituosas conforme as especificações técnicas."
        )
    ),

    DiagnosticCheck(
        title = "Investigação de oscilações na rede de alimentação",
        description = "Identificar possíveis variações provenientes da rede elétrica ou de outros equipamentos conectados à mesma instalação.",
        recommendedActions = listOf(
            "Verificar se outros equipamentos apresentam alterações de funcionamento.",
            "Investigar se as oscilações coincidem com partidas de grandes cargas.",
            "Utilizar analisador de qualidade de energia, quando necessário.",
            "Registrar as ocorrências e encaminhar problemas da rede à concessionária, quando aplicável."
        )
    )
)

// Verificações para falha elétrica na resistência do sistema de degelo
val defrostHeaterElectricalFaultChecks = listOf(

    DiagnosticCheck(
        title = "Inspeção visual das resistências",
        description = "Verificar se as resistências elétricas de degelo apresentam danos físicos, deformações, sinais de superaquecimento ou deterioração.",
        recommendedActions = listOf(
            "Inspecionar visualmente as resistências de degelo.",
            "Verificar sinais de queimadura, deformação ou rompimento.",
            "Avaliar as condições dos terminais e conexões.",
            "Substituir resistências que apresentem danos."
        )
    ),

    DiagnosticCheck(
        title = "Medição da resistência elétrica",
        description = "Medir a resistência elétrica do elemento de degelo para identificar possíveis interrupções ou alterações incompatíveis com suas características nominais.",
        recommendedActions = listOf(
            "Desenergizar o circuito antes da medição.",
            "Desconectar a resistência do circuito elétrico.",
            "Medir a resistência com um multímetro adequado.",
            "Comparar o resultado com os valores especificados pelo fabricante."
        )
    ),

    DiagnosticCheck(
        title = "Teste de isolamento elétrico",
        description = "Avaliar as condições do isolamento da resistência para identificar possíveis fugas de corrente para a carcaça ou aterramento.",
        recommendedActions = listOf(
            "Desenergizar e desconectar a resistência do circuito.",
            "Utilizar instrumento apropriado para o teste de isolamento.",
            "Verificar possíveis fugas para a carcaça ou aterramento.",
            "Comparar os resultados com as especificações do fabricante."
        )
    ),

    DiagnosticCheck(
        title = "Inspeção das conexões elétricas do sistema de degelo",
        description = "Verificar terminais, cabos, conectores e demais pontos de ligação da resistência em busca de mau contato, oxidação ou aquecimento.",
        recommendedActions = listOf(
            "Inspecionar os terminais e conectores da resistência.",
            "Verificar sinais de oxidação ou carbonização.",
            "Avaliar as condições dos cabos de alimentação.",
            "Corrigir conexões defeituosas e substituir componentes danificados."
        )
    ),

    DiagnosticCheck(
        title = "Monitoramento do consumo durante o degelo",
        description = "Avaliar o comportamento da corrente elétrica durante o ciclo de degelo para identificar consumo anormal ou falhas de funcionamento da resistência.",
        recommendedActions = listOf(
            "Verificar se o ciclo de degelo ocorre normalmente.",
            "Medir a corrente elétrica durante o funcionamento da resistência.",
            "Comparar o consumo com os valores nominais do componente.",
            "Investigar oscilações ou consumo incompatível com as especificações."
        )
    )
)

// Verificações para dimensionamento inadequado da proteção elétrica
val improperProtectionSizingChecks = listOf(

    DiagnosticCheck(
        title = "Conferência da corrente nominal do disjuntor",
        description = "Verificar se a corrente nominal do disjuntor está adequada à corrente de projeto e às características da carga.",
        recommendedActions = listOf(
            "Identificar a corrente nominal do disjuntor instalado.",
            "Verificar a corrente de projeto do circuito.",
            "Comparar os valores com as especificações do equipamento.",
            "Corrigir eventuais incompatibilidades conforme o projeto elétrico."
        )
    ),

    DiagnosticCheck(
        title = "Verificação da compatibilidade entre disjuntor e condutores",
        description = "Avaliar se a proteção está corretamente dimensionada em relação à capacidade de condução de corrente dos cabos instalados.",
        recommendedActions = listOf(
            "Identificar a seção e o tipo dos condutores utilizados.",
            "Verificar a capacidade de condução de corrente dos cabos.",
            "Considerar os fatores de correção aplicáveis à instalação.",
            "Verificar a coordenação entre a proteção e os condutores."
        )
    ),

    DiagnosticCheck(
        title = "Avaliação da curva de disparo do disjuntor",
        description = "Verificar se a curva de disparo do disjuntor é compatível com as características de partida e funcionamento das cargas conectadas.",
        recommendedActions = listOf(
            "Identificar a curva de disparo do disjuntor instalado.",
            "Avaliar as correntes de partida dos motores e compressores.",
            "Verificar a compatibilidade entre a curva de disparo e a aplicação.",
            "Adequar a proteção conforme o projeto e as especificações técnicas."
        )
    ),

    DiagnosticCheck(
        title = "Verificação da capacidade de interrupção",
        description = "Conferir se a capacidade de interrupção do disjuntor é suficiente para suportar a corrente de curto-circuito presumida no ponto de instalação.",
        recommendedActions = listOf(
            "Identificar a capacidade de interrupção indicada no disjuntor.",
            "Verificar a corrente de curto-circuito presumida no circuito.",
            "Comparar os valores conforme os requisitos do projeto.",
            "Especificar proteção compatível quando necessário."
        )
    ),

    DiagnosticCheck(
        title = "Análise das condições de instalação e operação",
        description = "Avaliar se as condições reais de instalação, temperatura ambiente e agrupamento dos componentes podem influenciar o funcionamento da proteção elétrica.",
        recommendedActions = listOf(
            "Verificar a temperatura ambiente no local de instalação.",
            "Avaliar possíveis influências do agrupamento de disjuntores.",
            "Verificar as condições de ventilação do quadro elétrico.",
            "Considerar os fatores de correção indicados pelo fabricante."
        )
    )
)