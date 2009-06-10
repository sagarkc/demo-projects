<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Story_Books.aspx.cs" Inherits="_Default" %>

<asp:Content ID="content4" ContentPlaceHolderID="ContentPlaceHolder1" runat="server" >
    &nbsp;<asp:GridView ID="GridView1" runat="server" AllowPaging="True" AllowSorting="True"
        AutoGenerateColumns="False" DataSourceID="SqlDataSource1" Style="position: relative" Height="17px" Width="324px">
    </asp:GridView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:RMSConnectionString2 %>"
        SelectCommand="SELECT * FROM [StoryBookNames]"></asp:SqlDataSource>
</asp:Content>

