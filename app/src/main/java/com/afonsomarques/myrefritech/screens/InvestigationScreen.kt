package com.afonsomarques.myrefritech.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.afonsomarques.myrefritech.generalPowerChecks
import com.afonsomarques.myrefritech.motorsInoperativeInvestigation
import com.afonsomarques.myrefritech.partialFailureQuestion

@Composable
fun InvestigationScreen(
    cause: String,
    onBackClick: () -> Unit
) {
    var currentStep by remember {
        mutableStateOf("INITIAL_QUESTION")
    }


    val checkResults = remember {
        mutableStateMapOf<Int, String>()
    }

    val investigation = when (cause) {
        "Motores de ventilação inoperantes" ->
            motorsInoperativeInvestigation
        else -> null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Investigação da possível causa",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = cause,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        when (currentStep) {

            "INITIAL_QUESTION" -> {

                Text(
                    text = investigation?.initialQuestion?.question
                        ?: "Investigação não cadastrada.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                investigation?.initialQuestion?.answers?.forEach { answer ->

                    Button(
                        onClick = {
                            currentStep = when (answer) {
                                "Sim" -> "GENERAL_POWER_CHECK"
                                "Não" -> "PARTIAL_FAILURE_QUESTION"
                                else -> "INITIAL_QUESTION"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        Text(answer)
                    }
                }
            }

            "PARTIAL_FAILURE_QUESTION" -> {

                Text(
                    text = partialFailureQuestion.question,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                partialFailureQuestion.answers.forEach { answer ->

                    Button(
                        onClick = {
                            currentStep = when (answer) {
                                "Sim" -> "INDIVIDUAL_MOTOR_CHECK"
                                "Não" -> "SUGGEST_LOW_ROTATION"
                                else -> "PARTIAL_FAILURE_QUESTION"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        Text(answer)
                    }
                }
            }

            "GENERAL_POWER_CHECK" -> {

                Text(
                    text = "Verificação da alimentação elétrica dos motores",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    itemsIndexed(generalPowerChecks) { index, check ->

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF1F3F5)
                            ),
                            border = BorderStroke(
                                1.dp,
                                MaterialTheme.colorScheme.outlineVariant
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    text = "${index + 1}. ${check.title}",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = check.description,
                                    fontSize = 14.sp
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = "Resultado da verificação:",
                                    fontWeight = FontWeight.SemiBold
                                )

                                listOf(
                                    "Normal",
                                    "Anormalidade encontrada",
                                    "Não verificado"
                                ).forEach { status ->

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                checkResults[index] = status
                                            }
                                            .padding(vertical = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        RadioButton(
                                            selected = checkResults[index] == status,
                                            onClick = {
                                                checkResults[index] = status
                                            }
                                        )

                                        Text(
                                            text = status,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                currentStep = "INVESTIGATION_SUMMARY"
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Concluir verificações")
                        }
                    }
                }
            }

            "INVESTIGATION_SUMMARY" -> {

                val normalCount = checkResults.values.count {
                    it == "Normal"
                }

                val abnormalCount = checkResults.values.count {
                    it == "Anormalidade encontrada"
                }

                val pendingCount = generalPowerChecks.size -
                        normalCount - abnormalCount

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    item {

                        Text(
                            text = "Resumo da investigação",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Verificações normais: $normalCount",
                            fontSize = 16.sp
                        )

                        Text(
                            text = "Anormalidades encontradas: $abnormalCount",
                            fontSize = 16.sp
                        )

                        Text(
                            text = "Não verificadas: $pendingCount",
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Detalhamento dos resultados",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    itemsIndexed(generalPowerChecks) { index, check ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {

                            Text(
                                text = check.title,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = checkResults[index] ?: "Não verificado"
                            )
                        }

                        HorizontalDivider()
                    }

                    item {

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Recomendações para as anormalidades",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    itemsIndexed(generalPowerChecks) { index, check ->

                        if (checkResults[index] == "Anormalidade encontrada") {

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFFFF3E0)
                                )
                            ) {

                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {

                                    Text(
                                        text = check.title,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    check.recommendedActions.forEach { action ->

                                        Text(
                                            text = "• $action",
                                            fontSize = 14.sp,
                                            modifier = Modifier.padding(vertical = 3.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {

                        if (abnormalCount == 0) {

                            Text(
                                text = "Nenhuma anormalidade foi registrada nas verificações realizadas. Isso não significa necessariamente que o equipamento esteja livre de falhas.",
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                currentStep = "GENERAL_POWER_CHECK"
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Voltar às verificações")
                        }
                    }
                }
            }

            "INDIVIDUAL_MOTOR_CHECK" -> {

                Text(
                    text = "Verificação individual dos micromotores",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }

            "SUGGEST_LOW_ROTATION" -> {

                Text(
                    text = "Recomenda-se investigar a possível causa: Motores com rotação abaixo do esperado.",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (
            currentStep != "GENERAL_POWER_CHECK" &&
            currentStep != "INVESTIGATION_SUMMARY"
        ) {
            Spacer(modifier = Modifier.weight(1f))
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        ) {
            Text("Voltar às possíveis causas")
        }
    }


}
