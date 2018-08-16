function loadLocale() {
    var lang = localStorage ? (localStorage.getItem('user-lang') || 'en') : 'en',
        file = Ext.util.Format.format("resources/locale/{0}.js", lang);
    extJsFile = Ext.util.Format.format("resources/locale/{0}.js", lang);
    Ext.Loader.loadScript({
            url: extJsFile, scope: this,
            onLoad: function () {

                console.log('Lang file successfully load!' + file);
            }
        }
    );
}

loadLocale();
/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('BizDash.Application', {
    extend: 'Ext.app.Application',

    name: 'BizDash',
    requires: [],

    quickTips: false,
    // platformConfig: {
    //     desktop: {
    //         quickTips: true
    //     }
    // },

    stores: [
        // TODO: add global / shared stores here
    ],

    init: function () {
        this.splashscreen = Ext.getBody().mask(  translations.loadingApp, 'splashscreen' );
    },
    launch: function () {
        this.splashscreen.fadeOut({
            duration: 0,
            remove:true
        });
        Ext.create(BizDash.view.login.Login);
        // TODO - Launch the application
    }

    // onAppUpdate: function () {
    //     Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
    //         function (choice) {
    //             if (choice === 'yes') {
    //                 window.location.reload();
    //             }
    //         }
    //     );
    // }
});
