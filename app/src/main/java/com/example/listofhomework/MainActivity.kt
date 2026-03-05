package com.example.listofhomework

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listofhomework.ui.theme.ListOfHomeworkTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListOfHomeworkTheme {

                listHomework()

            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun listHomework(modifier: Modifier = Modifier) {

    data class Tarefa(
        var nome: String,
        var status: Boolean = false,
        var descricao: String
    )

    val tarefas = mutableStateListOf<Tarefa>(
        Tarefa("Estudar Kotlin", true, "Programar"),
        Tarefa("Fazer exercícios", false, "Supino")

    )


    var tarefa: Tarefa = Tarefa("", false, "");

    var nome by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color(0xFF93C5FD)),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Homeworks", fontSize = 30.sp, fontWeight = FontWeight(800))
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFFF5F7FB))
                .padding(30.dp),
            horizontalAlignment = Alignment.Start,

        ) {


            OutlinedTextField(value = nome, onValueChange = { nome = it } ,
                label = { Text(text ="Digite o Titulo da Tarefa")})

            Spacer(modifier = Modifier.height(20.dp))

            TextField(value = nome, onValueChange = { nome = it } ,
                label = { Text(text ="Digite o que tem que fazer")})
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            itemsIndexed(tarefas) { index, tarefa ->

                Spacer(modifier = Modifier.height(25.dp))

                Surface(
                    modifier = Modifier
                        .width(350.dp)
                        .height(70.dp),
                    color = Color(0xFFEFEFEF),
                    shape = RoundedCornerShape(8.dp),


                    ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = tarefa.status,
                            onCheckedChange = { it ->
                                tarefas[index] = tarefa.copy(status = it)
                            }
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(text = "Titulo: ${tarefa.nome}", fontSize = 18.sp, fontWeight = FontWeight(800))
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "Descrição: ${tarefa.descricao}", fontSize = 15.sp)
                        }

                    }

                }

            }
        }


    }
}
