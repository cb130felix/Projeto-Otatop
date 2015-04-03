namespace POO___Gerador_de_Arquivo_Relacional
{
    partial class Entidade
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label7 = new System.Windows.Forms.Label();
            this.textBoxEntidade_NomeEntidade = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.ajudaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.comboBoxEntidade_cadEntidade = new System.Windows.Forms.ComboBox();
            this.btnOk_cadEntidade = new System.Windows.Forms.Button();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(10, 67);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(65, 13);
            this.label7.TabIndex = 44;
            this.label7.Text = "Intensidade:";
            this.label7.Click += new System.EventHandler(this.label7_Click);
            // 
            // textBoxEntidade_NomeEntidade
            // 
            this.textBoxEntidade_NomeEntidade.Location = new System.Drawing.Point(114, 41);
            this.textBoxEntidade_NomeEntidade.Name = "textBoxEntidade_NomeEntidade";
            this.textBoxEntidade_NomeEntidade.Size = new System.Drawing.Size(100, 20);
            this.textBoxEntidade_NomeEntidade.TabIndex = 43;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(11, 44);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(97, 13);
            this.label1.TabIndex = 42;
            this.label1.Text = "Nome da entidade:";
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ajudaToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(237, 24);
            this.menuStrip1.TabIndex = 47;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // ajudaToolStripMenuItem
            // 
            this.ajudaToolStripMenuItem.Name = "ajudaToolStripMenuItem";
            this.ajudaToolStripMenuItem.Size = new System.Drawing.Size(50, 20);
            this.ajudaToolStripMenuItem.Text = "Ajuda";
            // 
            // comboBoxEntidade_cadEntidade
            // 
            this.comboBoxEntidade_cadEntidade.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.comboBoxEntidade_cadEntidade.FormattingEnabled = true;
            this.comboBoxEntidade_cadEntidade.Items.AddRange(new object[] {
            "Forte",
            "Fraca"});
            this.comboBoxEntidade_cadEntidade.Location = new System.Drawing.Point(114, 67);
            this.comboBoxEntidade_cadEntidade.Name = "comboBoxEntidade_cadEntidade";
            this.comboBoxEntidade_cadEntidade.Size = new System.Drawing.Size(100, 21);
            this.comboBoxEntidade_cadEntidade.TabIndex = 77;
            this.comboBoxEntidade_cadEntidade.SelectedIndexChanged += new System.EventHandler(this.comboBox2_SelectedIndexChanged);
            // 
            // btnOk_cadEntidade
            // 
            this.btnOk_cadEntidade.Location = new System.Drawing.Point(81, 104);
            this.btnOk_cadEntidade.Name = "btnOk_cadEntidade";
            this.btnOk_cadEntidade.Size = new System.Drawing.Size(75, 23);
            this.btnOk_cadEntidade.TabIndex = 88;
            this.btnOk_cadEntidade.Text = "OK";
            this.btnOk_cadEntidade.UseVisualStyleBackColor = true;
            this.btnOk_cadEntidade.Click += new System.EventHandler(this.button1_Click);
            // 
            // Entidade
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(237, 139);
            this.Controls.Add(this.btnOk_cadEntidade);
            this.Controls.Add(this.comboBoxEntidade_cadEntidade);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.textBoxEntidade_NomeEntidade);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Entidade";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Entidade";
            this.Load += new System.EventHandler(this.Entidade_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox textBoxEntidade_NomeEntidade;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem ajudaToolStripMenuItem;
        private System.Windows.Forms.Button btnOk_cadEntidade;
        private System.Windows.Forms.ComboBox comboBoxEntidade_cadEntidade;
    }
}