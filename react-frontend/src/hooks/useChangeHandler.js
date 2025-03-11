export default function useChangeHandler(Object, eventObject, setter) {
    if ('checked' === eventObject.target) {
        setter({...Object, [eventObject.target.name]: eventObject.target.checked}); 
    }
    else if (eventObject.target.id) {
        setter({...Object, [eventObject.target.id]: eventObject.target.value});
    }
    else{
        setter({...Object, [eventObject.target.name]: eventObject.target.value});
    }
    // console.log(Object);
    
}