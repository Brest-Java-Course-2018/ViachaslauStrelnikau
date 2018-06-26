/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('BizDash.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',
    requires: [],

    alias: 'viewmodel.main',

    data: {
        name: 'Students,<p>Groups,<p>Avg Marks',
        loremIpsum: 'Group User managment test project.'
    }
    //TODO - add data, formulas and/or methods to support your view
});
