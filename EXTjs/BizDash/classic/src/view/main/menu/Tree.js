/**
 * Created by Odmin on 24.08.2018.
 */
Ext.define('Packt.view.menu.Tree', {
    requires: [
        'BizDash.view.main.menu.TreeController'
    ],
    extend: 'Ext.tree.Panel',
    xtype: 'menutree',
    controller:'tree',
    rootVisible: false,
    root: {
        expanded: true,
        children: [
            {
                text: translations.logo, iconCls: 'fa-graduation-cap', expanded: true, children:
                    [
                        {
                            text: translations.groups,
                            iconCls: 'fa-users',
                            leaf: true
                        },
                        {
                            text: translations.students,
                            iconCls: 'fa-user',
                            leaf: true
                        }
                    ]
            }
        ]
    },
    listeners:{
        itemclick:'onTreeStudentClick'
    }
});