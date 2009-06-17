using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.IO;

public partial class Indian_Movies : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        string path = Server.MapPath("Assets/Movies/Indian Languages");
        DirectoryInfo di = new DirectoryInfo(path);
        int i = 0;
        foreach (FileInfo fi in di.GetFiles())
        {
            HyperLink HL = new HyperLink();
            HL.ID = "HyperLink" + i++;
            HL.Text = fi.Name;
            HL.NavigateUrl = "downloading.aspx?file=" + fi.Name;
            this.Page.Controls.Add(HL);
            TableRow tr = new TableRow();
            TableCell cell = new TableCell();
            cell.Controls.Add(HL);
            tr.Cells.Add(cell);
            this.linksTable.Rows.Add(tr);
            //this.Page.Controls.Add(new LiteralControl("<br/>"));
        }
    }
}
