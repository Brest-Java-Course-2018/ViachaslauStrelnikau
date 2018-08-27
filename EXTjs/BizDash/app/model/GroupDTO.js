/**
 * Created by Odmin on 13.06.2018.
 */
Ext.define('BizDash.model.GroupDTO', {
    extend: 'Ext.data.Model',

    fields: [

        // The fields for this model. This is an Array of Ext.data.field.Field definition objects or simply the field name.
        // If just a name is given, the field type defaults to auto.  For example:

        { name: 'groupId',     type: 'int' },
        { name: 'fullName',      type: 'string' },
        { name: 'groupAvgMarks',    type: 'string' }

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

});