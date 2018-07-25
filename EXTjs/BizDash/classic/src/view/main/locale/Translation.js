/**
 * Created by Odmin on 26.06.2018.
 */
Ext.define('BizDash.view.main.locale.Translation', {
    extend: 'Ext.button.Split',
    xtype: 'translation',
    requires: [
        'BizDash.view.main.locale.TranslationController'
    ],
    controller: 'translation',
    menu: {               //#3
        xtype: 'menu',    //#4
        items: [
            {
                xtype: 'menuitem', //#5
                iconCls: 'ru',
                text: 'Русский'
            },
            {
                xtype: 'menuitem', //#6
                iconCls: 'en',
                text: 'English'
            }]
        ,
        defaults:{
            listeners: {
                click: 'onMenuItemClick'
            }
        }
    }
});