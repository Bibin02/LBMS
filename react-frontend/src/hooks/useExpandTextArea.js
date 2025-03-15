export default function useExpandTextArea(referenceObject, eventObject){
  const textarea = referenceObject.current;
   
  if (eventObject.key === "Enter") {
      textarea.style.height = "auto"; // Reset height
      textarea.style.height = textarea.scrollHeight + "px"; // Adjust height to fit content
    }

  if (eventObject.key === "Backspace" || eventObject.key === "Delete") {
      textarea.style.height = "auto"; // Reset height
      textarea.style.height = textarea.scrollHeight + "px"; // Adjust height to fit content
  }
  
}