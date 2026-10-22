package com.afonsomarques.myrefritech.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiagnosisScreen(
    symptom: String,
    onBackClick: () -> Unit
) {
    val possibleCauses = when (symptom) {
        "Ausência de ventilação ou baixa vazão de ar" -> listOf(
            "Motores de ventilação inoperantes",
            "Evaporador com excesso de gelo",
            "Colméias/grelhas de insuflamento obstruídas",
            "Motores com rotação abaixo do esperado",
            "Módulo de ventilação montado incorretamente",
            "Defletor ou direcionador de ar acima do evaporador ausente ou instalado incorretamente"
        )

        else -> emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Investigação técnica",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Sintoma selecionado:",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = symptom,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Possíveis causas a investigar",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (possibleCauses.isNotEmpty()) {
            possibleCauses.forEachIndexed { index, cause ->
                Text(
                    text = "${index + 1}. $cause",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    fontSize = 16.sp
                )
            }
        } else {
            Text(
                text = "A investigação deste sintoma será disponibilizada em uma próxima etapa.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Estas são hipóteses iniciais, não um diagnóstico definitivo. Confirme as condições do equipamento antes de concluir a investigação.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Voltar")
        }
    }
}

