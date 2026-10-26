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