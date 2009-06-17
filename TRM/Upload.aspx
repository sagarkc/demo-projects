<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Upload.aspx.cs" Inherits="Upload" %>

<asp:Content ID="content3" ContentPlaceHolderID="ContentPlaceHolder3" runat="server" >
<div>
    <table>
    <tr><td><asp:Label runat="server" ID="labelSelectFileType" Text="Select File Catagory:"></asp:Label>
        <asp:DropDownList ID="DropDownListUpload" runat="server" DataSourceID="SqlDataSource1"
            DataTextField="SubCatagoryName" DataValueField="SubCatagoryName">
        </asp:DropDownList></td>
    <td>
        &nbsp;<asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:RMSConnectionString2 %>"
            SelectCommand="SELECT [SubCatagoryName] FROM [Subcatagory] ORDER BY [SubCatagoryId]">
        </asp:SqlDataSource>
    </td></tr>
<tr><td><asp:Label ID="labelSelectFile" Text="Select File you want to upload" runat="server"></asp:Label></td></tr>
<tr><td style="height: 26px"><asp:FileUpload id="fileupload1" runat="server" /></td>
<td style="height: 26px"><asp:Button id="button1" runat="server" Text="Upload File" OnClick="buttonUploadFile_Click"/></td>
<td style="height: 26px"><asp:Button id="button2" runat="server" Text="Cancel"/></td>
</tr>
</table>
<asp:Label runat="server" ID="labelUploaded" Text=" "></asp:Label>
    </div>
</asp:Content>
