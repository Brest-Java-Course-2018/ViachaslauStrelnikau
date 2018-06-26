/**
 * Created by Odmin on 22.06.2018.
 */
Ext.define('BizDash.model.Language', {
    extend: 'Ext.data.Model',

    fields: [

        { name: 'id',     type: 'string' },
        { name: 'lang',      type: 'string' }

    ]

    /*
    Uncomment to add validation rules
    validators: {
        age: 'presence',
        name: { type: 'length', min: 2 },
        gender: { type: 'inclusion', list: ['Male', 'Female'] },
        username: [
            { type: 'exclusion', list: ['Admin', 'Operator'] },
            { type: 'format', matcher: /([a-z]+)[0-9]{2,3}/i }
        ]
    }
    */

    /*
    Uncomment to add a rest proxy that syncs data with the back end.
    proxy: {
        type: 'rest',
        url : '/users'
    }
    */
});