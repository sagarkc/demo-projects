using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Shell32;

namespace Log2Chart
{
    public partial class ReportGenerator : Form
    {
        public ReportGenerator()
        {
            InitializeComponent();
        }

        
        private void button1_Click(object sender, EventArgs e)
        {
            selectLogFolder();
        }

        

        private void button2_Click(object sender, EventArgs e)
        {
            RunReport rr = new RunReport(textBoxLogFilePath.Text);
            GenericArrayList<LogLine> allLogLines = rr.process();
            richTextBox1.AppendText("Total lines : " + allLogLines.Count+"\n");
            richTextBox1.AppendText("Processing Start @ " + DateTime.Now + "\n");
            PleaseWaitDialog dlg = new PleaseWaitDialog();
            dlg.ShowDialog(this);
            rr.generateReport();
        }

        

        private void selectLogFolder()
        {
            string strPath = "";
            string strCaption = "Select a directory";
            DialogResult result;

            ShellClass shell = new ShellClass();
            Folder2 folder = (Folder2)shell.BrowseForFolder(0, strCaption, 0,
                System.Reflection.Missing.Value);
            if (folder == null)
            {
                result = DialogResult.Cancel;
            }
            else
            {
                strPath = folder.Self.Path;
                result = DialogResult.OK;
            }
            if (result == DialogResult.OK)
            {
                textBoxLogFilePath.Text = strPath;
            }
        }

        private void toolStripButtonRunReport_Click(object sender, EventArgs e)
        {
            new RunReport(textBoxLogFilePath.Text);
        }

        private void toolStripButtonOpenFile_Click(object sender, EventArgs e)
        {
            selectLogFolder();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            richTextBox1.Text = "";
        }
    }
}
