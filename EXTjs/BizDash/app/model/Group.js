/**
 * Created by Odmin on 13.06.2018.
 */
Ext.define('BizDash.model.Group', {
    extend: 'Ext.data.Model',

    fields: [

        // The fields for this model. This is an Array of Ext.data.field.Field definition objects or simply the field name.
        // If just a name is given, the field type defaults to auto.  For example:

        {name: 'groupId', type: 'number', defaultValue: 0,critical: true},
        {name: 'shortName', type: 'string'},
        {name: 'fullName', type: 'string'},
        {name: 'description', type: 'string'}
    ],
    validators: {
        shortName: { type: 'length', min: 2 ,max:50},
        fullName: { type: 'length', min: 2 ,max:255 },
        description: { type: 'length', min: 2 ,max:255 }
    },


    idProperty: 'groupId',
    proxy: {
        type: 'rest',
        url: 'http://127.0.0.1:8088/groups',
        actionMethods : {
            create  : 'POST',
            read    : 'GET',
            update  : 'POST',
            destroy : 'DELETE',
            appendId: true
        },
        reader: {
            type: 'json'
        },
        writer: {
            type: 'json',
            clientIdProperty: 'groupId',
            writeAllFields:true
        }
    }


});