namespace POO___Gerador_de_Arquivo_Relacional
{
    partial class Home
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Home));
            this.btn_GerarArquivo = new System.Windows.Forms.Button();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.inserirToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.entidadeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.atributoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.entidadeToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.relaçãoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.relaçõesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.bináriaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.ternáriaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.listarToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.listagemCompletaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.entidadesToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.atributosToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.relaçõesToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.ajudaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // btn_GerarArquivo
            // 
            this.btn_GerarArquivo.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btn_GerarArquivo.BackgroundImage")));
            this.btn_GerarArquivo.Font = new System.Drawing.Font("Arial Rounded MT Bold", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btn_GerarArquivo.Location = new System.Drawing.Point(96, 348);
            this.btn_GerarArquivo.Name = "btn_GerarArquivo";
            this.btn_GerarArquivo.Size = new System.Drawing.Size(95, 44);
            this.btn_GerarArquivo.TabIndex = 4;
            this.btn_GerarArquivo.Text = "Gerar Arquivo";
            this.btn_GerarArquivo.UseVisualStyleBackColor = true;
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.inserirToolStripMenuItem,
            this.listarToolStripMenuItem,
            this.ajudaToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(291, 24);
            this.menuStrip1.TabIndex = 6;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // inserirToolStripMenuItem
            // 
            this.inserirToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.entidadeToolStripMenuItem,
            this.atributoToolStripMenuItem,
            this.relaçõesToolStripMenuItem});
            this.inserirToolStripMenuItem.Name = "inserirToolStripMenuItem";
            this.inserirToolStripMenuItem.Size = new System.Drawing.Size(51, 20);
            this.inserirToolStripMenuItem.Text = "Inserir";
            // 
            // entidadeToolStripMenuItem
            // 
            this.entidadeToolStripMenuItem.Name = "entidadeToolStripMenuItem";
            this.entidadeToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.entidadeToolStripMenuItem.Text = "Entidade";
            this.entidadeToolStripMenuItem.Click += new System.EventHandler(this.entidadeToolStripMenuItem_Click);
            // 
            // atributoToolStripMenuItem
            // 
            this.atributoToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.entidadeToolStripMenuItem1,
            this.relaçãoToolStripMenuItem});
            this.atributoToolStripMenuItem.Name = "atributoToolStripMenuItem";
            this.atributoToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.atributoToolStripMenuItem.Text = "Atributos";
            this.atributoToolStripMenuItem.Click += new System.EventHandler(this.atributoToolStripMenuItem_Click);
            // 
            // entidadeToolStripMenuItem1
            // 
            this.entidadeToolStripMenuItem1.Name = "entidadeToolStripMenuItem1";
            this.entidadeToolStripMenuItem1.Size = new System.Drawing.Size(120, 22);
            this.entidadeToolStripMenuItem1.Text = "Entidade";
            this.entidadeToolStripMenuItem1.Click += new System.EventHandler(this.entidadeToolStripMenuItem1_Click);
            // 
            // relaçãoToolStripMenuItem
            // 
            this.relaçãoToolStripMenuItem.Name = "relaçãoToolStripMenuItem";
            this.relaçãoToolStripMenuItem.Size = new System.Drawing.Size(120, 22);
            this.relaçãoToolStripMenuItem.Text = "Relação";
            // 
            // relaçõesToolStripMenuItem
            // 
            this.relaçõesToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.bináriaToolStripMenuItem,
            this.ternáriaToolStripMenuItem});
            this.relaçõesToolStripMenuItem.Name = "relaçõesToolStripMenuItem";
            this.relaçõesToolStripMenuItem.Size = new System.Drawing.Size(152, 22);
            this.relaçõesToolStripMenuItem.Text = "Relações";
            // 
            // bináriaToolStripMenuItem
            // 
            this.bináriaToolStripMenuItem.Name = "bináriaToolStripMenuItem";
            this.bináriaToolStripMenuItem.Size = new System.Drawing.Size(117, 22);
            this.bináriaToolStripMenuItem.Text = "Binária";
            this.bináriaToolStripMenuItem.Click += new System.EventHandler(this.bináriaToolStripMenuItem_Click);
            // 
            // ternáriaToolStripMenuItem
            // 
            this.ternáriaToolStripMenuItem.Name = "ternáriaToolStripMenuItem";
            this.ternáriaToolStripMenuItem.Size = new System.Drawing.Size(117, 22);
            this.ternáriaToolStripMenuItem.Text = "Ternária";
            this.ternáriaToolStripMenuItem.Click += new System.EventHandler(this.ternáriaToolStripMenuItem_Click);
            // 
            // listarToolStripMenuItem
            // 
            this.listarToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.listagemCompletaToolStripMenuItem,
            this.entidadesToolStripMenuItem,
            this.atributosToolStripMenuItem,
            this.relaçõesToolStripMenuItem1});
            this.listarToolStripMenuItem.Name = "listarToolStripMenuItem";
            this.listarToolStripMenuItem.Size = new System.Drawing.Size(47, 20);
            this.listarToolStripMenuItem.Text = "Listar";
            // 
            // listagemCompletaToolStripMenuItem
            // 
            this.listagemCompletaToolStripMenuItem.Name = "listagemCompletaToolStripMenuItem";
            this.listagemCompletaToolStripMenuItem.Size = new System.Drawing.Size(177, 22);
            this.listagemCompletaToolStripMenuItem.Text = "Listagem Completa";
            // 
            // entidadesToolStripMenuItem
            // 
            this.entidadesToolStripMenuItem.Name = "entidadesToolStripMenuItem";
            this.entidadesToolStripMenuItem.Size = new System.Drawing.Size(177, 22);
            this.entidadesToolStripMenuItem.Text = "Entidades";
            // 
            // atributosToolStripMenuItem
            // 
            this.atributosToolStripMenuItem.Name = "atributosToolStripMenuItem";
            this.atributosToolStripMenuItem.Size = new System.Drawing.Size(177, 22);
            this.atributosToolStripMenuItem.Text = "Atributos";
            // 
            // relaçõesToolStripMenuItem1
            // 
            this.relaçõesToolStripMenuItem1.Name = "relaçõesToolStripMenuItem1";
            this.relaçõesToolStripMenuItem1.Size = new System.Drawing.Size(177, 22);
            this.relaçõesToolStripMenuItem1.Text = "Relações";
            // 
            // ajudaToolStripMenuItem
            // 
            this.ajudaToolStripMenuItem.Name = "ajudaToolStripMenuItem";
            this.ajudaToolStripMenuItem.Size = new System.Drawing.Size(50, 20);
            this.ajudaToolStripMenuItem.Text = "Ajuda";
            // 
            // Home
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(291, 404);
            this.Controls.Add(this.btn_GerarArquivo);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.MaximizeBox = false;
            this.Name = "Home";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Gerador de Arquivo Relacional";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btn_GerarArquivo;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem inserirToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem entidadeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem atributoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem relaçõesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem bináriaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem ternáriaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem listarToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem listagemCompletaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem entidadesToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem atributosToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem relaçõesToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem ajudaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem entidadeToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem relaçãoToolStripMenuItem;

    }
}

