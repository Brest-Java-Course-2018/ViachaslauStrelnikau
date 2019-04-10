/**
 * Created by Odmin on 28.08.2018.
 */
Ext.define('BizDash.view.main.MainMenu', {
    extend: 'Ext.panel.Panel',
    xtype: 'app-main',

    requires: [
        'BizDash.view.main.Main',
        'Packt.view.menu.Tree'
    ],

    plugins: 'viewport',

    /*
    Uncomment to give this component an xtype
    xtype: 'mainmenu',
    */

    // items: [
    //     {
    //         region: 'center',
    //         xtype:'main'
    //     },
    //     {
    //         region: 'west',
    //         width:250,
    //         collapsible:false,
    //         split:true,
    //         iconCls: 'fa fa-sitemap fa-lg', // #5
    //         title: translations.menu,
    //         xtype: 'menutree'
    //     }
    // ]
    //  title: 'Panel2',
    layout: 'border',
    width: 600,
    height: 400,

    defaults: {

        border: true,
        padding: 5
    },
    items: [
        {
            xtype: 'menutree',
            title: translations.menu,
            region: 'west',
            width: 200
        }
        ,
        {
            xtype: 'main',
            title: 'Center Region',
            region: 'center'
        }]

});