<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Login.aspx.cs" Inherits="Login" %>

<asp:Content ID="content5" ContentPlaceHolderID="ContentPlaceHolder1" runat="server" >
<asp:Table ID="table1" runat="server" style="left: 328px; position: absolute; top: 152px">
<asp:TableRow>
<asp:TableCell><asp:Label ID="label1" Text="Login Id:" runat="server"></asp:Label></asp:TableCell>
<asp:TableCell><asp:TextBox ID="textboxLogin" runat="server"></asp:TextBox></asp:TableCell>
</asp:TableRow>
<asp:TableRow>
<asp:TableCell><asp:Label ID="label2" Text="Password:" runat="server"></asp:Label></asp:TableCell>
<asp:TableCell><asp:TextBox ID="textboxPassword" runat="server"></asp:TextBox></asp:TableCell>
</asp:TableRow>
<asp:TableRow>
<asp:TableCell></asp:TableCell>
<asp:TableCell><asp:Button id="buttonLogin" Text="Login" runat="server" OnClick="buttonLogin_click" /><asp:Button id="buttonCancel" Text="Cancel" runat="server" OnClick="buttonCancel_click"/></asp:TableCell>
</asp:TableRow>
</asp:Table>
    </asp:Content>
