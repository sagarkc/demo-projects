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
using System.Threading;


public partial class dowmloading : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

        string filename = Request["file"].ToString();
        fileDownload(filename, Server.MapPath("~/Assets/Books/Technical Books/" + filename));
    }
    //public void DownloadFiles(string path)
    //{
    //    DirectoryInfo di = new DirectoryInfo(path);
    //    int i = 0;
    //    foreach (FileInfo fi in di.GetFiles())
    //    {
    //        HyperLink HL = new HyperLink();
    //        HL.ID = "HyperLink" + i++;
    //        HL.Text = fi.Name;
    //        HL.NavigateUrl = "downloading.aspx?file=" + fi.Name;
    //        this.Page.Controls.Add(HL);
    //        TableRow tr = new TableRow();
    //        TableCell cell = new TableCell();
    //        cell.Controls.Add(HL);
    //        tr.Cells.Add(cell);
    //        //this.Page.Controls.Add(new LiteralControl("<br/>"));
    //    }       
    //}

    private void fileDownload(string fileName, string fileUrl)
    {
        Response.Clear();
        bool success = ResponseFile(Page.Request, Page.Response, fileName, fileUrl, 1024000);
        if (!success)
            Response.Write("Downloading Error!");
        Response.End();
    }

    public bool ResponseFile(HttpRequest _Request, HttpResponse _Response, string _fileName, string _fullUrl, long _speed)
    {
        try
        {
            FileStream myFile = new FileStream(_fullUrl, FileMode.Open, FileAccess.Read, FileShare.ReadWrite);
            BinaryReader br = new BinaryReader(myFile);
            try
            {
                _Response.AddHeader("Accept-Ranges", "bytes");
                _Response.Buffer = false;
                long fileLength = myFile.Length;
                long startBytes = 0;
                int pack = 10240; //10K bytes
                int sleep = (int)Math.Floor((double)(1000 * pack / _speed)) + 1;
                if (_Request.Headers["Range"] != null)
                {
                    _Response.StatusCode = 206;
                    string[] range = _Request.Headers["Range"].Split(new char[] { '=', '-' });
                    startBytes = Convert.ToInt64(range[1]);
                }
                _Response.AddHeader("Content-Length", (fileLength - startBytes).ToString());
                if (startBytes != 0)
                {
                    _Response.AddHeader("Content-Range", string.Format(" bytes {0}-{1}/{2}", startBytes, fileLength - 1, fileLength));
                }
                _Response.AddHeader("Connection", "Keep-Alive");
                _Response.ContentType = "application/octet-stream";
                _Response.AddHeader("Content-Disposition", "attachment;filename="
                                    + HttpUtility.UrlEncode(_fileName, System.Text.Encoding.UTF8));
                br.BaseStream.Seek(startBytes, SeekOrigin.Begin);
                int maxCount = (int)Math.Floor((double)((fileLength - startBytes) / pack)) + 1;
                for (int i = 0; i < maxCount; i++)
                {
                    if (_Response.IsClientConnected)
                    {
                        _Response.BinaryWrite(br.ReadBytes(pack));
                        Thread.Sleep(sleep);
                    }
                    else
                    {
                        i = maxCount;
                    }
                }
            }
            catch
            {
                return false;
            }
            finally
            {
                br.Close();
                myFile.Close();
            }
        }
        catch
        {
            return false;
        }
        return true;
    }
}