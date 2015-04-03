using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace POO___Gerador_de_Arquivo_Relacional
{
    public partial class Entidade : Form
    {
        public Entidade()
        {
            InitializeComponent();
        }

        private void label7_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBoxEntidade_NomeEntidade.Text == string.Empty) //verifica se a caixa esta vazia
            {
                MessageBox.Show("Escolha um nome para sua entidade!");
            }
            else if (comboBoxEntidade_cadEntidade.Text == string.Empty)
            {
                MessageBox.Show("Selecione a Intensidade!");
            }

            else
            {
                string nomeEntidade = textBoxEntidade_NomeEntidade.Text; //pega o que foi escrito na textBox e joga na string
                string intensidadeEntidade = comboBoxEntidade_cadEntidade.Text; //pega o que foi escrito na comboBoxe joga na string
                List<string> cadEntidade = new List<string>() { "e " + nomeEntidade + " " + intensidadeEntidade }; // cria uma lista de string com o que peguei da textbox e combobox

                //Escrevendo Entidades no arquivo
                using (StreamWriter cadastroNomeEntidade = new StreamWriter(@"C:\GAR_gui\Arquivo.txt", true))
                {
                    foreach (var item in cadEntidade)
                    {
                        cadastroNomeEntidade.WriteLine(item);
                    }
                    MessageBox.Show("Cadastrado com sucesso no arquivo!");
                }

                
            }

        }

        private void Entidade_Load(object sender, EventArgs e)
        {

        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}
