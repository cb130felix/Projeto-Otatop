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
    public partial class Home : Form
    {
        public Home()
        {
            InitializeComponent();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void atributoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            
        }

        private void entidadeToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            InsereAtributo NewAtributo = new InsereAtributo();
            NewAtributo.ShowDialog();
        }

        private void bináriaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            RelacaoBinaria Relacao2 = new RelacaoBinaria();
            Relacao2.ShowDialog();
        }

        private void ternáriaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            RelacaoTernaria Relacao3 = new RelacaoTernaria();
            Relacao3.ShowDialog();
        }

        private void entidadeToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Entidade Entidade = new Entidade();
            Entidade.ShowDialog();
        }
    }
}
