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

public partial class Upload : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void buttonUploadFile_Click(object sender, EventArgs e)
    {
        if (fileupload1.HasFile)
        {
            labelUploaded.Text = "uploading...";
            fileupload1.SaveAs("C:\\" + fileupload1.FileName);
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

