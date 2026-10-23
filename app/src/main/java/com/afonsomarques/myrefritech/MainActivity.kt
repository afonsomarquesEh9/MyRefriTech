package com.afonsomarques.myrefritech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afonsomarques.myrefritech.ui.theme.MyRefriTechTheme
import com.afonsomarques.myrefritech.screens.HomeScreen
import com.afonsomarques.myrefritech.screens.SymptomScreen
import com.afonsomarques.myrefritech.screens.DiagnosisScreen
import android.net.Uri
import com.afonsomarques.myrefritech.screens.InvestigationScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MyRefriTechTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("home") {
                            HomeScreen(
                                onDiagnosticClick = {
                                    navController.navigate("equipment")
                                }
                            )
                        }


                        composable("equipment") {
                            EquipmentScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onEquipmentSelected = { equipment ->
                                    when (equipment) {
                                        "Sistema Rack" ->
                                            navController.navigate("rack")

                                        "Balcão expositor horizontal",
                                        "Balcão expositor vertical",
                                        "Balcão expositor de congelados" ->
                                            navController.navigate("symptoms/${Uri.encode(equipment)}")

                                        "Câmara fria" ->
                                            navController.navigate("refrigeration/${Uri.encode(equipment)}")

                                        else ->
                                            navController.navigate("unavailable")
                                    }
                                }
                            )
                        }


                        composable("rack") {
                            SimpleInfoScreen(
                                title = "Sistema Rack",
                                description = "A investigação técnica do Sistema Rack será implementada em uma etapa futura.",
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }


                        composable("refrigeration/{equipment}") { backStackEntry ->

                            val equipment = backStackEntry.arguments?.getString("equipment")
                                ?: "Equipamento não informado"

                            SimpleInfoScreen(
                                title = equipment,
                                description = "A investigação técnica deste equipamento será implementada em uma etapa futura.",
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }


                        composable("symptoms/{equipment}") { backStackEntry ->

                            val equipment = backStackEntry.arguments
                                ?.getString("equipment")
                                ?: "Equipamento não informado"

                            SymptomScreen(
                                equipment = equipment,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSymptomSelected = { symptom ->

                                    navController.getBackStackEntry("symptoms/{equipment}")
                                        .savedStateHandle
                                        .set("selected_symptom", symptom)

                                    navController.getBackStackEntry("symptoms/{equipment}")
                                        .savedStateHandle
                                        .set("selected_equipment", equipment)

                                    navController.navigate("diagnosis")
                                }
                            )
                        }


                        composable("diagnosis") {
                            val symptom = navController.getBackStackEntry("symptoms/{equipment}")
                                .savedStateHandle
                                .get<String>("selected_symptom")
                                ?: "Sintoma não informado"

                            DiagnosisScreen(
                                symptom = symptom,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCauseSelected = { cause ->
                                    navController.currentBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("selected_cause", cause)

                                    navController.navigate("investigation")
                                },
                                onFullScanClick = {
                                    navController.navigate("full_scan")
                                }
                            )
                        }

                        composable("investigation") {

                            val cause = navController.getBackStackEntry("diagnosis")
                                .savedStateHandle
                                .get<String>("selected_cause")
                                ?: "Causa não informada"

                            InvestigationScreen(
                                cause = cause,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }


                        composable("full_scan") {
                            SimpleInfoScreen(
                                title = "Varredura Técnica Completa",
                                description = "A sequência completa de verificações técnicas será implementada em uma próxima etapa.",
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }


                        composable("unavailable") {
                            SimpleInfoScreen(
                                title = "Opção indisponível",
                                description = "Diferentes tipos de equipamentos "+
                                "ainda não estão disponíveis nessa versão do MyRefriTech",
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun EquipmentScreen(
    onBackClick: () -> Unit,
    onEquipmentSelected: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Selecione o equipamento",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        EquipmentButton("Sistema Rack", onEquipmentSelected)

        EquipmentButton("Balcão expositor horizontal", onEquipmentSelected)

        EquipmentButton("Balcão expositor vertical", onEquipmentSelected)

        EquipmentButton("Balcão expositor de congelados", onEquipmentSelected)

        EquipmentButton("Câmara fria", onEquipmentSelected)

        EquipmentButton("Outro equipamento", onEquipmentSelected)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackClick
        ) {
            Text(text = "Voltar")
        }
    }
}

@Composable
fun EquipmentButton(
    name: String,
    onEquipmentSelected: (String) -> Unit
) {

    Button(
        onClick = {
            onEquipmentSelected(name)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = name)
    }
}

@Composable
fun SimpleInfoScreen(
    title: String,
    description: String,
    onBackClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = description,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBackClick
        ) {
            Text(text = "Voltar")
        }
    }
}

@Composable
fun RefrigerationScreen(
    equipment: String,
    onBackClick: () -> Unit,
    onSelfUnavailableClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = equipment,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selecione o sistema de refrigeração.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Implementaremos a seleção do sistema posteriormente.
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sistema Rack")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                onSelfUnavailableClick()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sistema Self")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Voltar")
        }
    }
}