/**
 * Created by Odmin on 14.06.2018.
 */
Ext.define('BizDash.model.StudentDTO', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'studentId', type: 'int'},
        {name: 'studentName', type: 'string'},
        {name: 'studentBirth', type: 'date', dateFormat: 'time'},
        {name: 'studentAvgMarks', type: 'float'},
        {name: 'fullName', type: 'string'}
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