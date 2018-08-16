/**
 * Created by Odmin on 30.07.2018.
 */
Ext.define('BizDash.view.login.Login', {
    extend: 'Ext.window.Window',

    xtype: 'login-dialog',             // #3

    requires: [
        'BizDash.view.login.LoginController'
    ],
    controller: 'login',

    autoShow: true,                    // #4
    height: 200,                       // #5
    width: 360,
    layout: {
        type: 'fit'                    // #7
    },
    iconCls: 'fa fa-key fa-lg',        // #8
    title: 'Login',                    // #9
    closeAction: 'hide',               // #10
    closable: false,                   // #11
    draggable: true,                  // #12
    resizable: false,                   // #13

    items: [
        {
            xtype: 'form',          //#14
            reference: 'form',
            bodyPadding: 15,        //#15
            defaults: {             //#16
                xtype: 'textfield', //#17
                anchor: '100%',     //#18
                labelWidth: 60,      //#19
                allowBlank: false, // #21
                vtype: 'alphanum', // #22
                minLength: 3,      // #23
                msgTarget: 'side', // #24
                enableKeyEvents: true,
                listeners: {
                    keypress: 'onTextFieldKeyPress'
                }
            },
            items: [
                {
                    name: 'user',
                    fieldLabel: 'User',
                    id: 'user',
                    maxLength: 25,
                    value: "loiane",
                },
                {
                    inputType: 'password',
                    name: 'password',
                    fieldLabel: 'password',
                    id: 'password',
                    maxLength: 15,
                    value: 'Packt123@1',
                    vtype: 'customPass'
                }
            ],
            dockedItems: [
                {
                    xtype: 'toolbar',
                    dock: 'bottom',
                    items: [
                        {xtype: 'tbfill'},
                        {
                            xtype: 'button', //#26
                            iconCls: 'fa fa-times fa-lg',
                            text: 'Cancel',
                            listeners: {
                                click: 'onButtonClickCancel'
                            }
                        },
                        {
                            xtype: 'button', //#27
                            formBind: true,  //#28
                            iconCls: 'fa fa-sign-in fa-lg',
                            text: 'Submit',
                            listeners: {
                                click: 'onButtonClickSubmit'
                            }
                        }
                    ]
                }
            ]
        }
    ]

});