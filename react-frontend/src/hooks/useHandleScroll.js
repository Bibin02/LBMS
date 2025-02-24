export default function useHandleScroll(trigger) {
    if (document.body.scrollHeight - 300 < window.scrollY + window.innerHeight) {
      trigger(true);
    }
}