<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Srory_Books.aspx.cs" Inherits="_Default" %>

<asp:Content ID="content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server" >
    <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" DataSourceID="SqlDataSource1">
        <Columns>
            <asp:BoundField DataField="BookName" HeaderText="BookName" SortExpression="BookName" />
            <asp:BoundField DataField="AuthorName" HeaderText="AuthorName" SortExpression="AuthorName" />
            <asp:BoundField DataField="AddedBy" HeaderText="AddedBy" SortExpression="AddedBy" />
        </Columns>
    </asp:GridView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:RMSConnectionString %>"
        SelectCommand="SELECT * FROM [StoryBookNames]"></asp:SqlDataSource>

</asp:Content>

