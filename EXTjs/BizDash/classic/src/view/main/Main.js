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

    requires: [
        'BizDash.store.LangStore',
        'BizDash.view.main.ListGroups',
        'BizDash.view.main.ListStudents',
        'BizDash.view.main.MainController',
        'BizDash.view.main.MainModel',
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
            bind: {
                text: '{name}'
            },
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
                xtype: 'combobox',
                id: 'langcombo',
                fieldLabel: '<span style="color: rgb(255, 255, 255); padding-left: 2px;">Language</span>',
                labelAlign: 'top',
                editable: false,
                displayField: 'lang',
                store: {
                    type: 'language'
                },
                queryMode: 'local',
                listeners: {
                    afterrender: function () {
                        var rec = this.store.find('id', navigator.language);
                        if (rec != -1) {
                            console.log(this.store.getAt(rec));
                            this.setValue(this.store.getAt(rec).get('lang'));
                            console.log(this);
                        }
                        else {
                            value : navigator.language;
                        }
                    }
                }
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

        title: 'Groups',
        iconCls: 'fa-users',
        layout: 'fit',
        // The following grid shares a store with the classic version's grid as well!
        items: [{
            xtype: 'listgroups'
        }]
    }, {
        title: 'Students',
        iconCls: 'fa-user',
        items: [{
            xtype: 'liststudents'
        }],

    },
        {
            title: 'About',
            iconCls: 'fa-anchor',
            bind: {
                html: '{loremIpsum}'
            }
        }
    ]


});
