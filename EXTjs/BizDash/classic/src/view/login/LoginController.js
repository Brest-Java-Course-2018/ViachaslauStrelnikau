/**
 * Created by Odmin on 31.07.2018.
 */
Ext.define('BizDash.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    requires: [
        'BizDash.util.SessionMonitor',
        'BizDash.util.Util',
        'BizDash.view.login.CapsLockTooltip',
        'BizDash.view.main.MainMenu',
        'Ext.form.action.Action'
    ],
    onTextFieldKeyPress: function (field, e, options) {

        if (e.getKey() === e.ENTER) {
            this.doLogin();
            return;
        }

        me = this;


        if (me.capslockTooltip === undefined||me.capslockTooltip === null) {
            me.capslockTooltip = Ext.widget('capslocktooltip');
            console.log('created tooltip');
        }

        var char = String.fromCharCode(e.getCharCode());
        console.log(char);
        if ((
                (e.shiftKey && char.toLowerCase() === char) ||
                (!e.shiftKey && char.toLowerCase() != char)) &&
            BizDash.util.Util.isLetter(char)) {
            console.log(me.capslockTooltip)
            me.capslockTooltip.show();
            console.log('show tooltip');
        }
        else {
            me.capslockTooltip.hide();
            console.log('hide tooltip');
        }


    }, // #5
    onButtonClickCancel: function (button, e, options) {
        console.log('Cancel login!');
        this.lookupReference('form').reset();

    }, // #6
    onButtonClickSubmit: function (button, e, options) {
        console.log('Starting logging login!');
        var me = this;
        if (me.lookupReference('form').isValid()) { // #1
            me.doLogin();              // #2
        }

    }, // #7
    doLogin: function () {
        var me = this;
        me.getView().mask('Authenticating... Please wait...');
        form = me.lookupReference('form');
        form.submit({
            clientValidation: true,        // #3
            url: 'http://127.0.0.1:8088/login/', // #4
            scope: me,         // #5
            success: 'onLoginSuccess',     // #6
            failure: 'onLoginFailure'      // #7
        })

    }, // #8
    onLoginFailure: function (form, action) {
        console.log(action);
        var result = BizDash.util.Util.decodeJSON(action.response.responseText, true)
        this.getView().unmask();
        switch (action.failureType) {
            case Ext.form.action.Action.CLIENT_INVALID:  //#5
                BizDash.util.Util.showErrorMsg('Form fields may not be submitted with invalid values');
                break;
            case Ext.form.action.Action.CONNECT_FAILURE:  //#6
                BizDash.util.Util.showErrorMsg('Form fields may not be submitted with invalid values');
                break;
            case Ext.form.action.Action.SERVER_INVALID:  //#7
                BizDash.util.Util.showErrorMsg(result.msg);
        }
    }, // #9
    onLoginSuccess: function (form, action) {
        var view = this.getView();
        view.unmask();
        view.close();
        Ext.create('BizDash.view.main.MainMenu');
        BizDash.util.SessionMonitor.start();

    }, // #10

    /**
     * Called when the view is created
     */
    init: function () {

    }

});