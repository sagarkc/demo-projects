<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/MasterPage.master" CodeFile="Technical_Books.aspx.cs" Inherits="TechnicalBooks" %>


<asp:Content ID="content6" ContentPlaceHolderID="ContentPlaceHolder1" runat="server" >
    &nbsp;&nbsp;
    <asp:GridView ID="GridView1" runat="server" AllowPaging="True" AllowSorting="True"
        AutoGenerateColumns="False" DataSourceID="SqlDataSource1" Height="244px" Width="432px">
        <Columns>
            <asp:BoundField DataField="BookName" HeaderText="BookName" SortExpression="BookName" />
            <asp:BoundField DataField="AuthorName" HeaderText="AuthorName" SortExpression="AuthorName" />
            <asp:BoundField DataField="AddedBy" HeaderText="AddedBy" SortExpression="AddedBy" />
            <asp:ButtonField CommandName="Select" Text="Download" />
            <asp:ButtonField CommandName="Select" Text="View" />
        </Columns>
    </asp:GridView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:RMSConnectionString2 %>"
        SelectCommand="SELECT [Images], [BookName], [AuthorName], [AddedBy] FROM [TechnicalBookNames]">
    </asp:SqlDataSource>
</asp:Content>

