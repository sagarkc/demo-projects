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

public partial class MasterPage : System.Web.UI.MasterPage
{
    protected void Page_Load(object sender, EventArgs e)
    {
       
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
