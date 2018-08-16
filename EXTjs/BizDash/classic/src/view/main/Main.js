/**
 * This class is the main view for the application. It is specified in app.js as the
 * "mainView" property. That setting automatically applies the "viewport"
 * plugin causing this view to become the body element (i.e., the viewport).
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('BizDash.view.main.Main', {
    extend: 'Ext.tab.Panel',
    xtype: 'app-main',
    plugins: 'viewport',

    requires: [
        'BizDash.view.main.ListGroups',
        'BizDash.view.main.ListStudents',
        'BizDash.view.main.MainController',
        'BizDash.view.main.MainModel',
        'BizDash.view.main.locale.Translation',
        'Ext.plugin.Viewport',
        'Ext.window.MessageBox'
    ],

    controller: 'main',
    viewModel: 'main',

    ui: 'navigation',

    tabBarHeaderPosition: 1,
    titleRotation: 0,
    tabRotation: 0,

    header: {
        layout: {
            align: 'stretch'
        },
        title: {
            text: translations.logo,
            flex: 0
        },
        iconCls: 'fa-graduation-cap'
    },

    tabBar: {
        flex: 1,
        layout: {
            align: 'stretch',
            overflowHandler: 'none'
        },
        xtype: 'panel',

        items: [
            {
                xtype:'translation'
            }
        ]
    },

    responsiveConfig: {
        tall: {
            headerPosition: 'top'
        },
        wide: {
            headerPosition: 'left'
        }
    },

    defaults: {
        bodyPadding: 20,
        tabConfig: {
            plugins: 'responsive',
            responsiveConfig: {
                wide: {
                    iconAlign: 'left',
                    textAlign: 'left'
                },
                tall: {
                    iconAlign: 'top',
                    textAlign: 'center',
                    width: 120
                }
            }
        }
    },

    items: [{

       // title: 'Groups',
        title: translations.groups,
        iconCls: 'fa-users',
        layout: 'fit',
        // The following grid shares a store with the classic version's grid as well!
        items: [{
            xtype: 'listgroups'
        }]
    }, {
        //title: 'Students',
        title: translations.students,
        iconCls: 'fa-user',
        items: [{
            xtype: 'liststudents'
        }],

    },
        {
            //title: 'About',
            title: translations.about,
            iconCls: 'fa-anchor',
            bind: {
                html: '{loremIpsum}'
            }
        }
    ]


});
