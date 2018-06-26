/**
 * Created by Odmin on 06.06.2018.
 */
Ext.define('BizDash.config.Config', {
    requires: [],
    extend: 'Ext.util.Observable',
        config: {
            version: '0.0.1-0',
            isPhone: false,
            isTablet: false,
            isDesktop: false
        },
        platformConfig: {
            phone: {isPhone: true},
            tablet: {isTablet: true},
            desktop: {isDesktop: true}
        },
        constructor: function (config) {
            this.initConfig(config);
            this.callParent([config]);
        },
        singleton: true,
        getBuildNumber: function () {
            var versionSplit = this.getVersion().split('-');
            return versionSplit[0];
        },
        applyVersion: function (newVersion, oldVersion) {
            return newVersion;
        },

        updateVersion: function (newVersion, oldVersion) {
            if (this.hasListeners) {
                this.fireEvent('versionchanged', newVersion, oldVersion);
            }
        }
    },
    function () {
        console.log('BizDash.config.Config defined');
        console.log(BizDash.config.Config.isInstance?'Singleton':'notSingleton');
        console.log(BizDash.config.Config.getIsDesktop()?'Desktop':'');
        console.log(BizDash.config.Config.getIsPhone()?'Phone':'');
        console.log(BizDash.config.Config.getIsTablet()?'Tablet':'');
    });