export default function useChangeHandler(Object, eventObject, setter) {
    if ('checkbox' === eventObject.target.type) {
        setter({...Object, [eventObject.target.id]: eventObject.target.checked}); 
    }
    else if (eventObject.target.id) {
        setter({...Object, [eventObject.target.id]: eventObject.target.value});
    }
    else{
        setter({...Object, [eventObject.target.name]: eventObject.target.value});
    }
    console.log(Object);
    
}

export function changeDataValue(jsonData, datakey, event, setter) {
    let addons = jsonData[datakey];
    addons[event.target.name] = event.target.value;

    setter({...jsonData, [datakey] : addons});
}

export function useAddHandler(jsonData, datakey, setter) {
    let addons = jsonData[datakey];
    addons["property"] = "value";

    setter({...jsonData, [datakey] : addons});
}

export function changeDataKey(jsonData, datakey, event, setter) {
    let addons = jsonData[datakey];
    let oldVal = addons[event.target.name];
    delete addons[event.target.name];
    addons[event.target.value] = oldVal;

    setter({...jsonData, [datakey] : addons});
}

export function useDeleteHandler(jsonData, datakey, datakeyinner, setter) {
    let addons = jsonData[datakey];
    delete addons[datakeyinner];
    setter({...jsonData, [datakey] : addons});
}