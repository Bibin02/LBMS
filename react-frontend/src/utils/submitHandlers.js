import { submitEditDataForm } from "../services/formSubmit";

export function defaultSubmitHandler(jsonData, destination, event, setPreviewMessage) {
    event.preventDefault();

    submitEditDataForm(jsonData, destination).then((apiStatus)=>{
        setPreviewMessage(apiStatus);
    })
    .catch((error_)=>{
        setPreviewMessage("! " + error_);
    })
}