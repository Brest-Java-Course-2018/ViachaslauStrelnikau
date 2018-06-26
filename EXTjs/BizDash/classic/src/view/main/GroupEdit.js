/**
 * Created by Odmin on 14.06.2018.
 */
Ext.define('BizDash.view.main.GroupEdit', {
    extend: 'Ext.window.Window',

    xtype: 'groupEditDialog',

    requires: [
        'BizDash.view.main.ListController'
    ],

    controller:'List',
    bind: {
        title: '{title}'
    },
    modal: true,
    closable: true,
    modelValidation: true,

    items: {
        xtype: 'form',
        reference: 'form',
        bodyPadding: 10,
        border: false,
        items: [{
            xtype: 'textfield',
            dataIndex: 'groupId',
            reference: 'groupId',
            bind: '{Group.groupId}',
            hidden: true
        }, {
            xtype: 'textfield',
            fieldLabel: 'Short Name',
            dataIndex: 'shortName',
            reference: 'shortName',
            bind: '{Group.shortName}',
            msgTarget: 'side',
            flex: 1

        }, {
            xtype: 'textfield',
            fieldLabel: 'Full Name',
            dataIndex: 'fullName',
            bind: '{Group.fullName}',
            msgTarget: 'side',
            grow: true,
            growMin: 200,
            growMax: 800
        }, {
            xtype: 'textarea',
            fieldLabel: 'Description',
            dataIndex: 'description',
            bind: '{Group.description}',
            msgTarget: 'side',
            grow: true
        }],
        buttons: [{
            text: 'Save',
            formBind: true,
            handler: 'onSaveGroupClick'
        }, {
            text: 'Cancel',
            handler: 'onCancelGroupClick'
        }]

    },

    initComponent: function() {
        this.renderTo = Ext.getBody();
        this.callParent(arguments);}
});