<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Upload.aspx.cs" Inherits="Upload" %>

<asp:Content ID="content3" ContentPlaceHolderID="ContentPlaceHolder3" runat="server" >
<div>
    <table>
    <tr><td><asp:Label runat="server" ID="labelSelectFileType" Text="Select File Catagory:"></asp:Label></td>
    <td><asp:DropDownList runat="server" ID="dropdownlistSelectType" CausesValidation="True" DataSourceID="SqlDataSource1" DataTextField="CatagoryName" DataValueField="CatagoryName"></asp:DropDownList><asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:RMSConnectionString2 %>"
        SelectCommand="SELECT [CatagoryName] FROM [dropdownListSearch]"></asp:SqlDataSource>
    <asp:RegularExpressionValidator id="RegularExpressionValidator1" runat="server" 
 ErrorMessage="Select catagories" 
 ControlToValidate="fileupload1"></asp:RegularExpressionValidator></td></tr>
<tr><td><asp:Label ID="labelSelectFile" Text="Select File you want to upload" runat="server"></asp:Label></td></tr>
<tr><td><asp:FileUpload id="fileupload1" runat="server" /></td>
<td><asp:Button id="button1" runat="server" Text="Upload File" OnClick="buttonUploadFile_Click"/></td>
<td><asp:Button id="button2" runat="server" Text="Cancel"/></td>
</tr>
</table>
<asp:Label runat="server" ID="labelUploaded" Text=" "></asp:Label>
    </div>
</asp:Content>
