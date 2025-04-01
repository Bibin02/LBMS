import Papa from 'papaparse'

export function removeSellerBook(bookUid) {
    return (bookUid+" book removed");
    
}

export function uploadSellerBooks(event, setMessage) {
    const file = event.target.files[0];

    if (!file) return;

    Papa.parse(file, {
        header: true, // Use the first row as headers
        skipEmptyLines: true,
        complete: (result) => {
            result.data.map((book)=>{
                let bd = {};
                const list = ["bookName","authors","publication","prize","discount","stock","keywords","isLend","lendDuration"]
                Object.entries(book).map(([key, value])=>{
                    if (!list.includes(key)) {
                        bd[key] = value
                    }
                })
                book.bookDescription = bd;
            })

            // console.log(result.data);
            let uploadStatus = true;
            let errorMsg = "Failed";
            if (uploadStatus) {
                setMessage("Uploaded Books into Database Successfully"); // JSON output
            }
            else{
                setMessage("! "+errorMsg)
            }
        },
        error: (errorMsg) =>{
            setMessage("! "+errorMsg);
        } 
    });
}