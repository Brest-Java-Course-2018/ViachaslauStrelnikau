Ext.define('BizDash.view.login.CapsLockTooltip', {
    extend: 'Ext.tip.QuickTip',

    xtype: 'capslocktooltip',

    target: 'password',
    anchor: 'top',
    anchorOffset: 0,
    width: 300,
    dismissDelay: 0,
    autoHide: false,

    title: '<div class="fa fa-exclamation-triangle">'+ translations.capsLockTitle+'</div>',
    html:  translations.capsLockMsg1+translations.capsLockMsg2+translations.capsLockMsg3+translations.capsLockMsg4

});