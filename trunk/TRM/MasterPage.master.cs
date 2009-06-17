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
using System.Xml;
using DataSet1TableAdapters;
using System.Data.SqlClient;
using System.IO;

public partial class MasterPage : System.Web.UI.MasterPage
{
    protected void Page_Load(object sender, EventArgs e)
    {
        CatagoryTableAdapter adp = new CatagoryTableAdapter();
        DataTable dt = adp.GetCatagoryName();
        DirectoryInfo diAsset = new DirectoryInfo(Server.MapPath("Assets"));
        for (int i = 0; i < dt.Rows.Count; i++)
        {
            string catagoryFolderName = dt.Rows[i][0].ToString();
            DirectoryInfo catagoryFolderDir = new DirectoryInfo(diAsset.FullName + "/" + catagoryFolderName);
            if (!catagoryFolderDir.Exists)
            {
                catagoryFolderDir.Create();
            }
            SubcatagoryTableAdapter subTa = new SubcatagoryTableAdapter();
            DataTable subDt = subTa.GetSubCatagoryNames(catagoryFolderName);
            for (int j = 0; j < subDt.Rows.Count; j++)
            {
                string subCatagoryFolderName = subDt.Rows[j][2].ToString();
                DirectoryInfo subCatagoryFolderDir = new DirectoryInfo(catagoryFolderDir + "/" + subCatagoryFolderName);
                if (!subCatagoryFolderDir.Exists)
                {
                    subCatagoryFolderDir.Create();
                }
            }
        }
    }
    protected void LinkButtonLog_Click(object sender, EventArgs e)
    {
        Response.Redirect("Login.aspx");
    }
    protected void buttonUpload_Click(object sender, EventArgs e)
    {
        if (Session != null)
        {
            if (Session["userID"] != null)
            {
                Response.Redirect("Upload.aspx");
            }
            else
            {
                Response.Redirect("Login.aspx");
            }
        }
        
    }

    protected void LinkButton5_Click(object sender, EventArgs e)
    {

    }
}
