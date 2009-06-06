using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace XmlSplitter
{
    public partial class XmlSplitterForm : Form
    {
        private string inputXmlFileName = "";
        private string outputXmlLocation = "";
        private ProductList pList = new ProductList();

        public XmlSplitterForm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            OpenFileDialog od = new OpenFileDialog();
            od.Filter = "XML Files | *.xml";
            od.Multiselect = false;
            DialogResult dr = od.ShowDialog(this);
            
            if (dr == DialogResult.OK)
            {
                inputXmlFileName = od.FileName;
                richTextBox1.AppendText("\n" + DateTime.Now + " ::- " + "File Selected { " + inputXmlFileName + " }");
                textBox1.Text = inputXmlFileName;
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            richTextBox1.AppendText("\n" + DateTime.Now + " ::- " + "Loading xml file.\n\nPlease wait ...\n");
            List<ProductDetail> pl = XmlProcessorUtil.processXml(inputXmlFileName);
            richTextBox1.AppendText("\n" + DateTime.Now + " ::- " + "Loading Complete");
            textBox2.Text = "" + pl.Count;
            pList.productCollection = pl;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            richTextBox1.AppendText("\n" + DateTime.Now + " ::- " + "Start split ...");
            int count = int.Parse(textBox3.Text);
            XmlProcessorUtil.split(count, pList.productCollection, outputXmlLocation);
            richTextBox1.AppendText("\n" + DateTime.Now + " ::- " + "Split complete");
        }

        private void button5_Click(object sender, EventArgs e)
        {
            FolderBrowserDialog fbd = new FolderBrowserDialog();
            DialogResult dr = fbd.ShowDialog(this);
            if (dr == DialogResult.OK)
            {
                outputXmlLocation = fbd.SelectedPath;
                textBox4.Text = outputXmlLocation;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            richTextBox1.Text = "";
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            if (textBox1.Text != null && textBox1.Text != "")
            {
                button4.Enabled = true;
            }
            else
            {
                button4.Enabled = false;
            }
        }

        private void textBox4_TextChanged(object sender, EventArgs e)
        {
            if (textBox4.Text != null && textBox4.Text != "")
            {
                button2.Enabled = true;
            }
            else
            {
                button2.Enabled = false;
            }
        }

        private void textBox3_TextChanged(object sender, EventArgs e)
        {
            
        }

        private void textBox3_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar < '0' || e.KeyChar > '9')
            {
                e.Handled = true;
            }
        }
    }
}
