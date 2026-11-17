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