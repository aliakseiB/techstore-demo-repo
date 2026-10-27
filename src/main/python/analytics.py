"""Analytics module for tech store sales reporting."""

from dataclasses import dataclass
from typing import Dict, List


@dataclass
class SaleRecord:
    """Represents a single sale transaction."""

    product_id: str
    quantity: int
    unit_price: float

    @property
    def total(self) -> float:
        """Calculate the total value of this sale."""
        return self.quantity * self.unit_price


def calculate_revenue(sales: List[SaleRecord]) -> float:
    """
    Calculate total revenue from a list of sales.

    Args:
        sales: List of sale records to aggregate.

    Returns:
        Total revenue as a float.
    """
    return sum(sale.total for sale in sales)


def group_by_product(sales: List[SaleRecord]) -> Dict[str, float]:
    """
    Group sales revenue by product ID.

    Args:
        sales: List of sale records to process.

    Returns:
        Dictionary mapping product ID to total revenue.
    """
    result: Dict[str, float] = {}
    for sale in sales:
        result[sale.product_id] = result.get(sale.product_id, 0.0) + sale.total
    return result


def calculate_product_summary(sales: List[SaleRecord], product_id: str) -> float:
    """
    Calculate the total sales and product ID summary for a specific product.

    Args:
        sales (List[SaleRecord]): List of sales records.
        product_id (str): The ID of the product to summarize.

    Returns:
        float: The total sales value for the given product ID.
    """
    total = sum(sale.total for sale in sales if sale.product_id == product_id)
    return total

def process_sales(sales: List[SaleRecord], discount_rate: float, threshold: float) -> float:
    """
    Process sales data with discounts and threshold.

    Args:
        sales (List[SaleRecord]): The sales records to process.
        discount_rate (float): The discount rate to apply.
        threshold (float): The maximum allowable value.

    Returns:
        float: Processed sales value.
    """
    try:
        result = sum(sale.total for sale in sales) * discount_rate
        return min(result, threshold)
    except ZeroDivisionError:
        return 0