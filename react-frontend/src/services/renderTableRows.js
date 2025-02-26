import React from "react";

export default function renderTableRows(data, parentKey = "") {
    return Object.entries(data).map(([key, value], index) => {
      
        const fullKey = parentKey ? `${parentKey} → ${key}` : key; // Keep track of nesting
    
        if (typeof value === "object" && value !== null) {
          return (renderTableRows(value, fullKey)); /* Recursively render nested objects */
        } 
        else {
          return (
            React.createElement(
                "tr",
                { className: "table-data-row", key: index },
                React.createElement("td", { className: "table-data-col user-property" }, fullKey),
                React.createElement("td", { className: "table-data-col user-property-value" }, String(value))
              )              
          );
        }
    });
}