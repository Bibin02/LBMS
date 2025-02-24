export default function useHandleScroll(trigger) {
  if (window.innerHeight + window.scrollY >= document.documentElement.scrollHeight - 1) {
    trigger(true);
  }
}