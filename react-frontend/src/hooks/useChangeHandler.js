export default function useChangeHandler(Object, eventObject, setter) {
    if ('checked' in eventObject.target) {
        setter({...Object, [eventObject.target.name]: eventObject.target.checked}); 
    }
    else{
        setter({...Object, [eventObject.target.name]: eventObject.target.value});
    }
}