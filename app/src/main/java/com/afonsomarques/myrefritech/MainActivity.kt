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
                                        "Sistema Rack" -> {
                                            navController.navigate("rack")
                                        }

                                        "Expositor horizontal",
                                        "Expositor vertical",
                                        "Expositor de congelados",
                                        "Câmara fria" -> {
                                            navController.navigate("refrigeration/$equipment")
                                        }

                                        else -> {
                                            navController.navigate("unavailable")
                                        }
                                    }
                                }
                            )
                        }

                        composable("rack") {
                            SimpleInfoScreen(
                                title = "Sistema Rack",
                                description = "Diagnóstico do sistema de refrigeração Rack.",
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("refrigeration/{equipment}") { backStackEntry ->
                            val equipment = backStackEntry.arguments?.getString("equipment")
                                ?: "Equipamento"

                            RefrigerationScreen(
                                equipment = equipment,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onSelfUnavailableClick = {
                                    navController.navigate("unavailable")
                                }
                            )
                        }

                        composable("unavailable") {
                            SimpleInfoScreen(
                                title = "Opção indisponível",
                                description = "O sistema Self ainda não está disponível " +
                                        "nesta versão do MyRefriTech. " +
                                        "Essa funcionalidade poderá ser incluída futuramente.",
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
fun HomeScreen(
    onDiagnosticClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "MyRefriTech",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Assistente Técnico de Refrigeração Comercial",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Apoio técnico para investigação de falhas " +
                    "em sistemas de refrigeração comercial.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onDiagnosticClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Diagnóstico guiado")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                // Implementaremos a biblioteca posteriormente.
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Biblioteca técnica")
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

        EquipmentButton("Expositor horizontal", onEquipmentSelected)

        EquipmentButton("Expositor vertical", onEquipmentSelected)

        EquipmentButton("Expositor de congelados", onEquipmentSelected)

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