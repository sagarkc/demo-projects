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
using System.Data.SqlClient;
using System.Data;
using DataSet1TableAdapters;
using System.IO;

public partial class Upload : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {



    }
    protected void buttonUploadFile_Click(object sender, EventArgs e)
    {
        SubcatagoryTableAdapter adp = new SubcatagoryTableAdapter();
        DataTable subCatagorytable = adp.GetAllSubcatagoryNames();
        for (int i = 0; i < subCatagorytable.Rows.Count; i++)
        {
            if (DropDownListUpload.SelectedValue == subCatagorytable.Rows[i][2].ToString())
            {
                if (fileupload1.HasFile)
                {
                    labelUploaded.Text = "uploading...";
                    SubcatagoryTableAdapter catagoryadp = new SubcatagoryTableAdapter();
                    DataTable catagoryNamesTable = catagoryadp.GetCatagorySubcatagoryNames(DropDownListUpload.SelectedValue.ToString());
                    string path = Server.MapPath("Assets" + "/" + catagoryNamesTable.Rows[0][4].ToString() + "/" + subCatagorytable.Rows[i][2].ToString());
                    fileupload1.SaveAs(path + "/" + fileupload1.FileName);
                    labelUploaded.Text = "File name: " +
                             fileupload1.PostedFile.FileName + "<br>" +
                             fileupload1.PostedFile.ContentLength + " kb<br>" +
                             "Content type: " +
                             fileupload1.PostedFile.ContentType;
                }
                else
                {
                    labelUploaded.Text = "You have not specified a file.";
                }
            }
        }
     }
 }


